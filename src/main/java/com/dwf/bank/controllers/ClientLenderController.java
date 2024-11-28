package com.dwf.bank.controllers;

import com.dwf.bank.models.Client_Lender;
import com.dwf.bank.services.ClientLenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Client_Lender>> getAllClientLenders() {
        List<Client_Lender> clientLenders = clientLenderService.getAllClientLenders();
        return ResponseEntity.ok(clientLenders);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String,String>> addClient(@RequestBody Client_Lender clientLender) {
        clientLenderService.save(clientLender);
        Map<String,String> response = new HashMap<>();
        response.put("mensaje", "Cliente agregado con ID: " + clientLender.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateClient(@PathVariable UUID id, @RequestBody Client_Lender clientLender) {
        Client_Lender updatedClient = clientLenderService.update(id, clientLender);
        Map<String,String> response = new HashMap<>();
        response.put("mensaje", "Cliente actualizado con ID: " + updatedClient.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteClient(@PathVariable UUID id) {
        clientLenderService.delete(id);
        Map<String,String> response = new HashMap<>();
        response.put("mensaje", "Cliente desactivado con ID: " + id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client_Lender> getClientById(@PathVariable UUID id) {
        Client_Lender client = clientLenderService.get(id);
        return ResponseEntity.ok(client);
    }
}
