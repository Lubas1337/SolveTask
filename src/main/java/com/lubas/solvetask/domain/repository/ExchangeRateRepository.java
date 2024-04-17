package com.lubas.solvetask.domain.repository;

import com.lubas.solvetask.domain.models.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    List<ExchangeRate> findByBaseCurrencyAndTargetCurrencyAndType(String baseCurrency, String targetCurrency, String type);

    @Query(value = "SELECT * " +
            "FROM exchange_rate " +
            "WHERE DAY(date) = DAY(CURRENT_DATE)",
            nativeQuery = true)
    Optional<ExchangeRate> checkToday();

}
