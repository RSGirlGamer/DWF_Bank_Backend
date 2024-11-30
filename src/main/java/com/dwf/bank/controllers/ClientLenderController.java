package com.dwf.bank.controllers;

import com.dwf.bank.models.Client_Lender;
import com.dwf.bank.services.ClientLenderService;
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
    @GetMapping("/by-username")
    @PreAuthorize(RolePermissions.TODOS_LOS_ROLES)
    public ResponseEntity<Client_Lender> getClientByUsername(@RequestParam String username){
    	Client_Lender client = clientLenderService.getByUsername(username);
    	if(client!= null) {
    		return ResponseEntity.ok(client);
    	}
    	return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    @PreAuthorize(RolePermissions.TODOS_MENOS_GERENTE_GENERAL)
    public ResponseEntity<Map<String,String>> addClient(@RequestBody Client_Lender clientLender) {
        clientLenderService.save(clientLender);
        Map<String,String> response = new HashMap<>();
        response.put("mensaje", "Cliente agregado con ID: " + clientLender.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize(RolePermissions.TODOS_LOS_ROLES)
    public ResponseEntity<Map<String,String>> updateClient(@PathVariable UUID id, @RequestBody Client_Lender clientLender) {
        Client_Lender updatedClient = clientLenderService.update(id, clientLender);
        Map<String,String> response = new HashMap<>();
        response.put("mensaje", "Cliente actualizado con ID: " + updatedClient.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(RolePermissions.DEPENDIENTE_Y_SUPERIORES)
    public ResponseEntity<Map<String, String>> deleteClient(@PathVariable UUID id) {
        clientLenderService.delete(id);
        Map<String,String> response = new HashMap<>();
        response.put("mensaje", "Cliente desactivado con ID: " + id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize(RolePermissions.TODOS_LOS_ROLES)
    public ResponseEntity<Client_Lender> getClientById(@PathVariable UUID id) {
        Client_Lender client = clientLenderService.get(id);
        return ResponseEntity.ok(client);
    }
}
