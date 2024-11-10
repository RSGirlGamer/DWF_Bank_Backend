package com.dwf.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.dwf.bank.models.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}