package com.dwf.bank.controllers;

import com.dwf.bank.models.Loan;
import com.dwf.bank.services.LoanService;
import com.dwf.bank.util.RolePermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
    
    @GetMapping
    @PreAuthorize(RolePermissions.GERENTES_Y_CAJEROS)
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @PostMapping("/create")
    @PreAuthorize(RolePermissions.SOLO_CAJEROS)
    public ResponseEntity<String> createLoan(@RequestBody Loan loan, @RequestParam(required = false) UUID openedBy) {
        Loan savedLoan = loanService.save(loan, openedBy);
        return ResponseEntity.ok("Préstamo creado con ID: " + savedLoan.getId());
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize(RolePermissions.SOLO_GERENTES)
    public ResponseEntity<String> rejectLoan(@PathVariable UUID id, @RequestParam(required = false) UUID reviewedBy) {
        loanService.rejectLoan(id, reviewedBy);
        return ResponseEntity.ok("Préstamo rechazado con ID: " + id);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize(RolePermissions.SOLO_GERENTES)
    public ResponseEntity<String> approveLoan(@PathVariable UUID id, @RequestParam(required = false) UUID reviewedBy) {
        loanService.approveLoan(id, reviewedBy);
        return ResponseEntity.ok("Préstamo aprobado con ID: " + id);
    }

    @GetMapping("/{id}")
    @PreAuthorize(RolePermissions.TODOS_LOS_ROLES)
    public ResponseEntity<Loan> getLoanById(@PathVariable UUID id) {
        Loan loan = loanService.get(id);
        return ResponseEntity.ok(loan);
    }
}
