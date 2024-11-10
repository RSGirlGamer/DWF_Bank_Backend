package com.dwf.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.dwf.bank.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
    User findByUsername(String username);

}