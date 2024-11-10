package com.dwf.bank.controllers;

import com.dwf.bank.models.Movements;
import com.dwf.bank.services.MovementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movements")
public class MovementsController {

    @Autowired
    private MovementsService movementsService;
    
    @GetMapping
    public ResponseEntity<List<Movements>> getAllMovements() {
        List<Movements> movements = movementsService.getAllMovements();
        return ResponseEntity.ok(movements);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMovement(@RequestBody Movements movement) {
        movementsService.save(movement);
        return ResponseEntity.ok("Movimiento agregado con ID: " + movement.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movements> getMovementById(@PathVariable UUID id) {
        Movements movement = movementsService.get(id);
        return ResponseEntity.ok(movement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovement(@PathVariable UUID id) {
        movementsService.delete(id);
        return ResponseEntity.ok("Movimiento eliminado con ID: " + id);
    }
}
