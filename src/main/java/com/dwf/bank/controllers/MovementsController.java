package com.dwf.bank.controllers;

import com.dwf.bank.models.Movements;
import com.dwf.bank.services.MovementsService;
import com.dwf.bank.util.RolePermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movements")
public class MovementsController {

    @Autowired
    private MovementsService movementsService;
    
    @GetMapping
    @PreAuthorize(RolePermissions.TODOS_LOS_ROLES)
    public ResponseEntity<List<Movements>> getAllMovements() {
        List<Movements> movements = movementsService.getAllMovements();
        return ResponseEntity.ok(movements);
    }

    @PostMapping("/add")
    @PreAuthorize(RolePermissions.TODOS_LOS_ROLES)
    public ResponseEntity<String> addMovement(@RequestBody Movements movement) {
        movementsService.save(movement);
        return ResponseEntity.ok("Movimiento agregado con ID: " + movement.getId());
    }

    @GetMapping("/{id}")
    @PreAuthorize(RolePermissions.TODOS_LOS_ROLES)
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
