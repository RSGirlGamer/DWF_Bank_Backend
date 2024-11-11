package com.dwf.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import com.dwf.bank.models.Account;
import com.dwf.bank.models.Client_Lender;
import com.dwf.bank.repositories.AccountRepository;
import com.dwf.bank.repositories.ClientLenderRepository;

@Service
public class AccountService {

	@Autowired
    private ClientLenderRepository clientLenderRepository;
	
    @Autowired
    private AccountRepository accountRepository;
    
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account get(UUID id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account save(Account account) {
        account.setId(UUID.randomUUID());  // Asigna un UUID manualmente antes de persistir
        
        System.out.println(account.getId());
    	Client_Lender clientLender = clientLenderRepository.findById(account.getClient().getId()).orElse(null);
    	System.out.println(clientLender);
    	
    	if (clientLender == null) {
            throw new RuntimeException("Cliente no encontrado con el ID: " + account.getClient().getId());
        }

        // Verificamos cuántas cuentas tiene el cliente
        Set<Account> existingAccounts = clientLender.getAccounts(); // Suponiendo que la relación es ManyToMany
        System.out.println(existingAccounts);
        if (existingAccounts.size() >= 3) {
       	 return null;
        }

        // Si el cliente tiene menos de 3 cuentas, procedemos a agregar la nueva cuenta
        existingAccounts.add(account); // Agregar la cuenta a la lista de cuentas del cliente
        clientLender.setAccounts(existingAccounts); // Actualizamos la relación
        clientLenderRepository.save(clientLender); // Guardamos los cambios

       return accountRepository.save(account);
    }

    public Account update(UUID id, Account account) {
        if (accountRepository.existsById(id)) {
            account.setId(id);
            return accountRepository.save(account);
        }
        return null;
    }

    public void delete(UUID id) {
        accountRepository.deleteById(id);
    }
}
