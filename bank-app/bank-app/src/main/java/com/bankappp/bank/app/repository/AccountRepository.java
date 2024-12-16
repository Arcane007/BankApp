package com.bankappp.bank.app.repository;

import com.bankappp.bank.app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
