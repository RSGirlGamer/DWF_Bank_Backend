package com.dwf.bank.controllers;

import com.dwf.bank.models.Account;
import com.dwf.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    @PreAuthorize("hasRole('Cliente') or hasRole('Gerente_General')")
    public ResponseEntity<List<Account>> getAllAccounts() {
        System.out.println("Roles actuales del usuario: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }


    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> addAccount(@RequestBody Account account) {
        Account savedAccount = accountService.save(account);
        Map<String,Object> response = new HashMap<>();
        response.put("Cuenta", savedAccount);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateAccount(@PathVariable UUID id, @RequestBody Account account) {
        Account updatedAccount = accountService.update(id, account);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Cuenta actualizada con ID: " + updatedAccount.getId());
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteAccount(@PathVariable UUID id) {
        accountService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Cuenta eliminada con ID: " + id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable UUID id) {
        Account account = accountService.get(id);
        return ResponseEntity.ok(account);
    }
}
