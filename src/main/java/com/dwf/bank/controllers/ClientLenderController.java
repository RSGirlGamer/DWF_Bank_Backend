package com.dwf.bank.controllers;

import com.dwf.bank.models.Client_Lender;
import com.dwf.bank.services.ClientLenderService;
import com.dwf.bank.util.RolePermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientLenderController {

    @Autowired
    private ClientLenderService clientLenderService;
    
    @GetMapping
    @PreAuthorize(RolePermissions.TODOS_LOS_ROLES)
    public ResponseEntity<List<Client_Lender>> getAllClientLenders() {
        List<Client_Lender> clientLenders = clientLenderService.getAllClientLenders();
        return ResponseEntity.ok(clientLenders);
    }

    @PostMapping("/add")
    @PreAuthorize(RolePermissions.TODOS_MENOS_GERENTE_GENERAL)
    public ResponseEntity<String> addClient(@RequestBody Client_Lender clientLender) {
        clientLenderService.save(clientLender);
        return ResponseEntity.ok("Cliente agregado con ID: " + clientLender.getId());
    }

    @PutMapping("/{id}")
    @PreAuthorize(RolePermissions.TODOS_LOS_ROLES)
    public ResponseEntity<String> updateClient(@PathVariable UUID id, @RequestBody Client_Lender clientLender) {
        Client_Lender updatedClient = clientLenderService.update(id, clientLender);
        return ResponseEntity.ok("Cliente actualizado con ID: " + updatedClient.getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(RolePermissions.DEPENDIENTE_Y_SUPERIORES)
    public ResponseEntity<String> deleteClient(@PathVariable UUID id) {
        clientLenderService.delete(id);
        return ResponseEntity.ok("Cliente desactivado con ID: " + id);
    }

    @GetMapping("/{id}")
    @PreAuthorize(RolePermissions.TODOS_LOS_ROLES)
    public ResponseEntity<Client_Lender> getClientById(@PathVariable UUID id) {
        Client_Lender client = clientLenderService.get(id);
        return ResponseEntity.ok(client);
    }
}
