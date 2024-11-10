package com.dwf.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.dwf.bank.models.Client_Lender;
import com.dwf.bank.repositories.ClientLenderRepository;

@Service
public class ClientLenderService {

    @Autowired
    private ClientLenderRepository clientLenderRepository;
    
    public List<Client_Lender> getAllClientLenders() {
        return clientLenderRepository.findAll();
    }

    public Client_Lender get(UUID id) {
        return clientLenderRepository.findById(id).orElse(null);
    }

    public Client_Lender save(Client_Lender clientLender) {
    	clientLender.setId(UUID.randomUUID());  // Asigna un UUID manualmente antes de persistir
        return clientLenderRepository.save(clientLender);
    }

    public Client_Lender update(UUID id, Client_Lender clientLender) {
        if (clientLenderRepository.existsById(id)) {
            clientLender.setId(id);
            return clientLenderRepository.save(clientLender);
        }
        return null;
    }

    public void delete(UUID id) {
        clientLenderRepository.deleteById(id);
    }
}
