package com.dwf.bank.controllers;

import com.dwf.bank.models.Movements;
import com.dwf.bank.services.MovementsService;
import com.dwf.bank.util.RolePermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Map<String,String>> addMovement(@RequestBody Movements movement) {
        movementsService.save(movement);
        Map<String,String> response = new HashMap<>();
        response.put("Transacción", ""+movement.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize(RolePermissions.TODOS_LOS_ROLES)
    public ResponseEntity<Movements> getMovementById(@PathVariable UUID id) {
        Movements movement = movementsService.get(id);
        return ResponseEntity.ok(movement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteMovement(@PathVariable UUID id) {
        movementsService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("Deleted", ""+id);
        return ResponseEntity.ok(response);
    }
}
