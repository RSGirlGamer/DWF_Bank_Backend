package com.dwf.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.dwf.bank.models.Bank_Clerk;
import com.dwf.bank.repositories.BankClerkRepository;

@Service
public class BankClerkService {

    @Autowired
    private BankClerkRepository bankClerkRepository;
    
    public List<Bank_Clerk> getAllBankClerks() {
        return bankClerkRepository.findAll();
    }

    public Bank_Clerk get(UUID id) {
        return bankClerkRepository.findById(id).orElse(null);
    }

    public Bank_Clerk save(Bank_Clerk bankClerk) {
    	bankClerk.setId(UUID.randomUUID());  // Asigna un UUID manualmente antes de persistir
        return bankClerkRepository.save(bankClerk);
    }

    public Bank_Clerk update(UUID id, Bank_Clerk bankClerk) {
        if (bankClerkRepository.existsById(id)) {
            bankClerk.setId(id);
            return bankClerkRepository.save(bankClerk);
        }
        return null;
    }

    public void delete(UUID id) {
        bankClerkRepository.deleteById(id);
    }
}
