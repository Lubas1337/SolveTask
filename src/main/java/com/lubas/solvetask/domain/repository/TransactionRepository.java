package com.lubas.solvetask.domain.repository;

import com.lubas.solvetask.domain.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * " +
            "FROM transaction " +
            "WHERE YEAR(datetime) = YEAR(CURRENT_DATE) AND MONTH(datetime) = MONTH(CURRENT_DATE) " +
            "AND expense_category = :category AND account_from_id = :id " +
            "ORDER BY datetime DESC " +
            "LIMIT 1",
            nativeQuery = true)
    Optional<Transaction> findLastThisMonth(String category, Long id);
}
