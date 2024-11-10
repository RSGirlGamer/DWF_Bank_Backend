package com.dwf.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.dwf.bank.models.Client_Lender;

public interface ClientLenderRepository extends JpaRepository<Client_Lender, UUID> {
}