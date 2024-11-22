package com.dwf.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.dwf.bank.models.User;
import com.dwf.bank.models.UserRole;
import com.dwf.bank.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User get(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user, UUID createdBy) {
    	user.setId(UUID.randomUUID());  // Asigna un UUID manualmente antes de persistir
    	if(user.getRole() == UserRole.Cajero || user.getRole() == UserRole.Gerente_de_Sucursal) {
    		user.setStatus("Inactivo");
    	} else {
            user.setStatus("Activo"); // Cambiar el estado a inactivo
    	}
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	// Si 'createdBy' es distinto a nulo, lo asignamos al parámetro 'createdBy' recibido
        if (createdBy != null) {
            user.setCreatedBy(createdBy);  // Asignamos el UUID del creador del usuario
        }

        // Guardamos el nuevo usuario en la base de datos
        user = userRepository.save(user);

        // Si el campo 'createdBy' sigue siendo nulo después de guardar (en caso de que el parámetro 'createdBy' haya sido nulo),
        // actualizamos el campo 'createdBy' con el ID del usuario recién creado
        if (user.getCreatedBy() == null) {
            user.setCreatedBy(user.getId());  // Asignamos el UUID del mismo usuario creado
            userRepository.save(user);  // Actualizamos el usuario con el 'createdBy' asignado
        }
        return userRepository.save(user);
    }

    public User update(UUID id, User user) {
    	if (userRepository.existsById(id)) {
            User existingUser = userRepository.findById(id).get();
            // Verificar si la contraseña fue cambiada
            if (!user.getPassword().equals(existingUser.getPassword())) {
                // Encriptar la nueva contraseña
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                // Mantener la contraseña existente si no fue modificada
                user.setPassword(existingUser.getPassword());
            }
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    public void delete(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setStatus("Inactivo"); // Cambiar el estado a inactivo
            userRepository.save(user);  // Guardar el usuario con el nuevo estado
        }
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user; // Retornar el usuario si las credenciales son válidas
        }
        return null;
    }

}
