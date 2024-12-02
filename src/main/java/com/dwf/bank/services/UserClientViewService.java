package com.dwf.bank.services;

import com.dwf.bank.models.UserClientView;
import com.dwf.bank.repositories.UserClientViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserClientViewService {

    private final UserClientViewRepository userClientViewRepository;

    @Autowired
    public UserClientViewService(UserClientViewRepository userClientViewRepository) {
        this.userClientViewRepository = userClientViewRepository;
    }

    /**
     * Busca un usuario por su username con validación.
     *
     * @param username el nombre de usuario.
     * @return la entidad UserClientView si se encuentra.
     * @throws IllegalArgumentException si el username es nulo o está vacío.
     * @throws IllegalStateException si no se encuentra el usuario.
     */
    public UserClientView findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo o vacío.");
        }

        return userClientViewRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("No se encontró ningún usuario con el nombre de usuario: %s", username)));
    }

    /**
     * Busca un cliente por su email con validacion.
     *
     * @param email el correo electrónico del cliente.
     * @return la entidad UserClientView si se encuentra.
     * @throws IllegalArgumentException si el email es nulo o está vacio.
     * @throws IllegalStateException si no se encuentra el cliente.
     */
    public UserClientView findByClientEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico no puede ser nulo o vacío.");
        }

        return userClientViewRepository.findByClientEmail(email)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("No se encontró ningún cliente con el correo electrónico: %s", email)));
    }
}
