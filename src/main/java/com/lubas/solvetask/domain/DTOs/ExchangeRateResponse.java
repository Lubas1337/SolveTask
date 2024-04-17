package com.lubas.solvetask.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.util.Map;

@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponse {
    private Map<String, Double> rates;

    @JsonProperty("rates")
    public Map<String, Double> getRates() {
        return rates;
    }

}

