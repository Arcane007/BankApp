package com.bankappp.bank.app.controller;

import com.bankappp.bank.app.dto.AccountDto;
import com.bankappp.bank.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add endpoints for creating, updating, deleting, and retrieving accounts
    @PostMapping("/addaccount")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return  new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
    }

    @GetMapping("/getaccount/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return accountDto!= null? new ResponseEntity<>(accountDto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/deposite")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody Map<String,Double> request) {

      Double amount = request.get("amount");
      AccountDto accountDto = accountService.deepositeAccount(id, amount);

      return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawAmmount(@PathVariable Long id, @RequestBody Map<String,Double> request)
    {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdrawAmmount(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/getallaccounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }

}
