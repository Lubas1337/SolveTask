package com.lubas.solvetask.domain.repository;

import com.lubas.solvetask.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(Integer accountNumber);
}
