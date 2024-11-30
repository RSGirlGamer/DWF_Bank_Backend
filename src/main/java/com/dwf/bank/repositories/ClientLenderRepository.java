package com.dwf.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import com.dwf.bank.models.Client_Lender;

public interface ClientLenderRepository extends JpaRepository<Client_Lender, UUID> {
	@Query(value= "Select * FROM Client_From_Username WHERE username = :username", nativeQuery =true)
	Client_Lender findByUsername(@Param("username") String username);

}