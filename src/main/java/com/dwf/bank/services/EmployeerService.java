package com.dwf.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.dwf.bank.models.Employeer;
import com.dwf.bank.models.User;
import com.dwf.bank.repositories.EmployeerRepository;
import com.dwf.bank.repositories.UserRepository;

@Service
public class EmployeerService {

    @Autowired
    private EmployeerRepository employeerRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<Employeer> getAllEmployeers() {
        return employeerRepository.findAll();
    }

    public Employeer get(UUID id) {
        return employeerRepository.findById(id).orElse(null);
    }

    public Employeer save(Employeer employeer) {
    	employeer.setId(UUID.randomUUID());  // Asigna un UUID manualmente antes de persistir
        return employeerRepository.save(employeer);
    }

    public Employeer update(UUID id, Employeer employeer) {
        if (employeerRepository.existsById(id)) {
            employeer.setId(id);
            return employeerRepository.save(employeer);
        }
        return null;
    }
    
 // Función para rechazar un empleado, registrar quién lo rechazó
    public String rejectEmployeer(UUID employeerId, UUID rejectedById) {
        // Obtener el empleado
        Employeer employeer = employeerRepository.findById(employeerId).orElse(null);

        if (employeer == null) {
            return "Empleado no encontrado.";
        }

        // Cambiar el estado a "rechazado"
        employeer.setStatus("Rechazado");
        employeer.setRevised_by(rejectedById); // Registrar quién rechazó el empleado

        // Guardar el empleado con su nuevo estado
        employeerRepository.save(employeer);

        return "Empleado rechazado correctamente.";
    }

    // Función para aprobar un empleado, cambiar el estado a aprobado y activar el usuario
    public String approveEmployeer(UUID employeerId, UUID approvedById) {
        // Obtener el empleado
        Employeer employeer = employeerRepository.findById(employeerId).orElse(null);

        if (employeer == null) {
            return "Empleado no encontrado.";
        }

        // Cambiar el estado a "aprobado"
        employeer.setStatus("Aprobado");
        employeer.setRevised_by(approvedById); // Registrar quién aprobó al empleado

        // Cambiar el estado del usuario asociado a "activo"
        User user = employeer.getUser(); // Obtener el usuario asociado al empleado
        if (user != null) {
            user.setStatus("active"); // Cambiar el estado del usuario a "activo"
            userRepository.save(user); // Guardar el usuario con el nuevo estado
        }

        // Guardar el empleado con su nuevo estado
        employeerRepository.save(employeer);

        return "Empleado aprobado y usuario activado correctamente.";
    }

    public void delete(UUID id) {
        employeerRepository.deleteById(id);
    }
}
