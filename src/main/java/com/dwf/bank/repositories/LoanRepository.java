package com.dwf.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.dwf.bank.models.Loan;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
}