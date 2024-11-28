package com.dwf.bank.controllers;

import com.dwf.bank.models.Bank_Clerk;
import com.dwf.bank.services.BankClerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Map<String, String>> addBankClerk(@RequestBody Bank_Clerk bankClerk) {
        Bank_Clerk savedBankClerk = bankClerkService.save(bankClerk);
        Map<String, String> response = new HashMap<>();
        response.put("Bank Clerk", ""+bankClerk.getId());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateBankClerk(@PathVariable UUID id, @RequestBody Bank_Clerk bankClerk) {
        Bank_Clerk updatedBankClerk = bankClerkService.update(id, bankClerk);
        Map<String, String> response = new HashMap<>();
        response.put("Bank Clerk", ""+id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBankClerk(@PathVariable UUID id) {
        bankClerkService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("Bank Clerk", ""+id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank_Clerk> getBankClerkById(@PathVariable UUID id) {
        Bank_Clerk bankClerk = bankClerkService.get(id);
        return ResponseEntity.ok(bankClerk);
    }
}
