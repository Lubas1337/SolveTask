package com.lubas.solvetask.infrastructure.service;

import com.lubas.solvetask.domain.DTOs.ExchangeRateResponse;
import com.lubas.solvetask.domain.models.ExchangeRate;
import com.lubas.solvetask.domain.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {
    private final RestTemplate restTemplate;

    private final ExchangeRateRepository exchangeRateRepository;

    @Value("${exchange.api.url}")
    private String apiUrl;

    @Value("${exchange.api.app-id}")
    private String appId;

    @Value("${exchange.api.base}")
    private String baseCurrency;

    @Value("${exchange.api.symbols}")
    private String symbols;

    public void fetchAndSaveExchangeRates() {
        String url = apiUrl + "?app_id=" + appId + "&base=" + baseCurrency + "&symbols=" + symbols;
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        if (response != null && response.getRates() != null) {

            List<ExchangeRate> previousExchangeRatesKZT = exchangeRateRepository.findByBaseCurrencyAndTargetCurrencyAndType("USD", "KZT", "Closed");
            List<ExchangeRate> previousExchangeRatesRUB = exchangeRateRepository.findByBaseCurrencyAndTargetCurrencyAndType("USD", "RUB", "Closed");

            for (ExchangeRate previousRate : previousExchangeRatesKZT) {
                previousRate.setType("PreviousClose");
                exchangeRateRepository.save(previousRate);
            }

            for (ExchangeRate previousRate : previousExchangeRatesRUB) {
                previousRate.setType("PreviousClose");
                exchangeRateRepository.save(previousRate);
            }

            ExchangeRate exchangeRateKZT = new ExchangeRate(null, "USD", "KZT", response.getRates().get("KZT"), LocalDateTime.now(), "Closed");
            ExchangeRate exchangeRateRUB = new ExchangeRate(null, "USD", "RUB", response.getRates().get("RUB"), LocalDateTime.now(), "Closed");

            exchangeRateRepository.save(exchangeRateKZT);
            exchangeRateRepository.save(exchangeRateRUB);
        }
    }

    public double checkToday(){
        return exchangeRateRepository.checkToday().get().getRate();
    }

    @Scheduled(cron = "0 0 0 * * ?") // запускается каждый день в полночь
    //@Scheduled(fixedDelay = 10000) // запускается каждый 10000мс
    public void updateExchangeRatesDaily() {
        fetchAndSaveExchangeRates();
    }
}