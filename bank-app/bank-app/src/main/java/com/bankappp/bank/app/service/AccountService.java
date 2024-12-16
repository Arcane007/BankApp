package com.bankappp.bank.app.service;

import com.bankappp.bank.app.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account);

    AccountDto getAccountById(Long id);

    AccountDto deepositeAccount(Long id, double amount);

    AccountDto withdrawAmmount(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);
}