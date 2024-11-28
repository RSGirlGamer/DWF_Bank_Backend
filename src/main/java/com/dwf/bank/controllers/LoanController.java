package com.dwf.bank.controllers;

import com.dwf.bank.models.Loan;
import com.dwf.bank.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
    
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createLoan(@RequestBody Loan loan, @RequestParam(required = false) UUID openedBy) {
        Loan savedLoan = loanService.save(loan, openedBy);
        Map<String, String> response = new HashMap<>();
        response.put("Préstamo", ""+savedLoan.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Map<String, String>> rejectLoan(@PathVariable UUID id, @RequestParam(required = false) UUID reviewedBy) {
        loanService.rejectLoan(id, reviewedBy);
        Map<String, String> response = new HashMap<>();
        response.put("id", ""+id);
        response.put("reviewedBy", ""+reviewedBy);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Map<String, String>> approveLoan(@PathVariable UUID id, @RequestParam(required = false) UUID reviewedBy) {
        loanService.approveLoan(id, reviewedBy);
        Map<String, String> response = new HashMap<>();
        response.put("id", ""+id);
        response.put("reviewedBy", ""+reviewedBy);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable UUID id) {
        Loan loan = loanService.get(id);
        return ResponseEntity.ok(loan);
    }
}
