package com.dwf.bank.controllers;

import com.dwf.bank.models.Bank_Clerk;
import com.dwf.bank.services.BankClerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bank-clerks")
public class BankClerkController {

    @Autowired
    private BankClerkService bankClerkService;
    
    @GetMapping
    public ResponseEntity<List<Bank_Clerk>> getAllBankClerks() {
        List<Bank_Clerk> bankClerks = bankClerkService.getAllBankClerks();
        return ResponseEntity.ok(bankClerks);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBankClerk(@RequestBody Bank_Clerk bankClerk) {
        Bank_Clerk savedBankClerk = bankClerkService.save(bankClerk);
        return ResponseEntity.ok("Dependiente bancario agregado con ID: " + savedBankClerk.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBankClerk(@PathVariable UUID id, @RequestBody Bank_Clerk bankClerk) {
        Bank_Clerk updatedBankClerk = bankClerkService.update(id, bankClerk);
        return ResponseEntity.ok("Dependiente bancario actualizado con ID: " + updatedBankClerk.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBankClerk(@PathVariable UUID id) {
        bankClerkService.delete(id);
        return ResponseEntity.ok("Dependiente bancario desactivado con ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank_Clerk> getBankClerkById(@PathVariable UUID id) {
        Bank_Clerk bankClerk = bankClerkService.get(id);
        return ResponseEntity.ok(bankClerk);
    }
}
