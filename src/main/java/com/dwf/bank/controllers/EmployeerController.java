package com.dwf.bank.controllers;

import com.dwf.bank.models.Employeer;
import com.dwf.bank.services.EmployeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeerController {

    @Autowired
    private EmployeerService employeerService;
    
    @GetMapping
    public ResponseEntity<List<Employeer>> getAllEmployeers() {
        List<Employeer> employeers = employeerService.getAllEmployeers();
        return ResponseEntity.ok(employeers);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<String> rejectEmployee(@PathVariable UUID id, @RequestParam UUID rejectedBy) {
        employeerService.rejectEmployeer(id, rejectedBy);
        return ResponseEntity.ok("Empleado rechazado con ID: " + id);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<String> approveEmployee(@PathVariable UUID id, @RequestParam UUID approvedBy) {
        employeerService.approveEmployeer(id, approvedBy);
        return ResponseEntity.ok("Empleado aprobado con ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employeer> getEmployeeById(@PathVariable UUID id) {
        Employeer employeer = employeerService.get(id);
        return ResponseEntity.ok(employeer);
    }
}
