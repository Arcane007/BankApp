package com.bankappp.bank.app.service.impl;

import com.bankappp.bank.app.dto.AccountDto;
import com.bankappp.bank.app.entity.Account;
import com.bankappp.bank.app.exception.AccountException;
import com.bankappp.bank.app.mapper.AccountMapper;
import com.bankappp.bank.app.repository.AccountRepository;
import com.bankappp.bank.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {

       Account account = accountRepository.findById(id).orElseThrow(()->new AccountException("Account not found"));
       AccountDto accountDto = AccountMapper.mapToAccountDto(account);
       return accountDto;
    }

    @Override
    public AccountDto deepositeAccount(Long id, double amount) {

        Account account = accountRepository.findById(id).orElseThrow(()->new AccountException("Account not found"));
        AccountDto accountDto = AccountMapper.mapToAccountDto(account);
        account.setBalance(account.getBalance() + amount);
        Account savedAccount=accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawAmmount(Long id, double amount) {

        Account account = accountRepository.findById(id).orElseThrow(()->new AccountException("Account not found"));

        if(account.getBalance() < amount)
        {
            throw new AccountException("Insufficient balance");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> accountsList = accountRepository.findAll();
        return  accountsList.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository.findById(id).orElseThrow(()->new AccountException("Account not found"));
         accountRepository.deleteById(id);

    }
}
