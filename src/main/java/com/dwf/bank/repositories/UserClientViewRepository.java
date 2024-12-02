package com.dwf.bank.repositories;

import com.dwf.bank.models.UserClientView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserClientViewRepository extends JpaRepository<UserClientView, UUID> {

    Optional<UserClientView> findByUsername(String username);

    Optional<UserClientView> findByClientEmail(String email);
}