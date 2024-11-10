package com.dwf.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dwf.bank.models.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
}