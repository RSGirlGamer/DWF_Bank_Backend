package com.dwf.bank.controllers;

import com.dwf.bank.models.Client_Lender;
import com.dwf.bank.services.ClientLenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<String> addClient(@RequestBody Client_Lender clientLender) {
        clientLenderService.save(clientLender);
        return ResponseEntity.ok("Cliente agregado con ID: " + clientLender.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable UUID id, @RequestBody Client_Lender clientLender) {
        Client_Lender updatedClient = clientLenderService.update(id, clientLender);
        return ResponseEntity.ok("Cliente actualizado con ID: " + updatedClient.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable UUID id) {
        clientLenderService.delete(id);
        return ResponseEntity.ok("Cliente desactivado con ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client_Lender> getClientById(@PathVariable UUID id) {
        Client_Lender client = clientLenderService.get(id);
        return ResponseEntity.ok(client);
    }
}
