package com.dwf.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.dwf.bank"})
public class BankDwfApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankDwfApplication.class, args);
	}

}
