package com.dwf.bank.controllers;

import com.dwf.bank.models.Loan;
import com.dwf.bank.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<String> createLoan(@RequestBody Loan loan, @RequestParam(required = false) UUID openedBy) {
        Loan savedLoan = loanService.save(loan, openedBy);
        return ResponseEntity.ok("Préstamo creado con ID: " + savedLoan.getId());
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<String> rejectLoan(@PathVariable UUID id, @RequestParam(required = false) UUID reviewedBy) {
        loanService.rejectLoan(id, reviewedBy);
        return ResponseEntity.ok("Préstamo rechazado con ID: " + id);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<String> approveLoan(@PathVariable UUID id, @RequestParam(required = false) UUID reviewedBy) {
        loanService.approveLoan(id, reviewedBy);
        return ResponseEntity.ok("Préstamo aprobado con ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable UUID id) {
        Loan loan = loanService.get(id);
        return ResponseEntity.ok(loan);
    }
}
