package com.dwf.bank.controllers;

import com.dwf.bank.models.Account;
import com.dwf.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        Account savedAccount = accountService.save(account);
        return ResponseEntity.ok("Cuenta agregada con ID: " + savedAccount.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable UUID id, @RequestBody Account account) {
        Account updatedAccount = accountService.update(id, account);
        return ResponseEntity.ok("Cuenta actualizada con ID: " + updatedAccount.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable UUID id) {
        accountService.delete(id);
        return ResponseEntity.ok("Cuenta eliminada con ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable UUID id) {
        Account account = accountService.get(id);
        return ResponseEntity.ok(account);
    }
}
