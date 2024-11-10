package com.dwf.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.dwf.bank.models.Movements;
import com.dwf.bank.repositories.MovementsRepository;

@Service
public class MovementsService {

    @Autowired
    private MovementsRepository movementsRepository;
    
    public List<Movements> getAllMovements() {
        return movementsRepository.findAll();
    }

    public Movements get(UUID id) {
        return movementsRepository.findById(id).orElse(null);
    }

    public Movements save(Movements movements) {
    	movements.setId(UUID.randomUUID());  // Asigna un UUID manualmente antes de persistir
        return movementsRepository.save(movements);
    }

    public Movements update(UUID id, Movements movements) {
        if (movementsRepository.existsById(id)) {
            movements.setId(id);
            return movementsRepository.save(movements);
        }
        return null;
    }

    public void delete(UUID id) {
        movementsRepository.deleteById(id);
    }
}
