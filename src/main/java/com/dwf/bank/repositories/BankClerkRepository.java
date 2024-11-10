package com.dwf.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.dwf.bank.models.Bank_Clerk;

public interface BankClerkRepository extends JpaRepository<Bank_Clerk, UUID> {
}