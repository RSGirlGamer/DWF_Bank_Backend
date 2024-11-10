package com.dwf.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import com.dwf.bank.models.Employeer;
import com.dwf.bank.models.Loan;
import com.dwf.bank.repositories.EmployeerRepository;
import com.dwf.bank.repositories.LoanRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private EmployeerRepository employeerRepository;
    
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan get(UUID id) {
        return loanRepository.findById(id).orElse(null);
    }

    public Loan save(Loan loan, UUID createdBy) {
    	Employeer employeer = employeerRepository.findById(createdBy).orElse(null);
        if(employeer == null || employeer.getStatus() != "Activo") {
        	return null;
        }
        
        if(loan.getAccount().getClient().getSalary() < 365 && loan.getAmount() < 10000 && loan.getInterests() < 3) {
        	return null;
        }
        
        if(loan.getAccount().getClient().getSalary() < 600 && loan.getAmount() < 25000 && loan.getInterests() < 3) {
        	return null;
        }
        
        if(loan.getAccount().getClient().getSalary() < 900 && loan.getAmount() < 35000 && loan.getInterests() < 4) {
        	return null;
        }
        
        if(loan.getAccount().getClient().getSalary() < 1000 && loan.getAmount() < 50000 && loan.getInterests() < 5) {
        	return null;
        }
        
        if(loan.getMontly_payment() > (loan.getAccount().getClient().getSalary() * 0.30)) {
        	return null;
        }
    	loan.setId(UUID.randomUUID());  // Asigna un UUID manualmente antes de persistir
        loan.setOpened_by(employeer);
        return loanRepository.save(loan);
    }

    public Loan update(UUID id, Loan loan) {
        if (loanRepository.existsById(id)) {
            loan.setId(id);
            return loanRepository.save(loan);
        }
        return null;
    }
    
 // Función para rechazar un préstamo
    public String rejectLoan(UUID loanId, UUID rejectedById) {
        // Obtener el préstamo
        Loan loan = loanRepository.findById(loanId).orElse(null);

        if (loan == null) {
            return "Préstamo no encontrado.";
        }
        
        Employeer employeer = employeerRepository.findById(rejectedById).orElse(null);
        if(employeer == null || employeer.getStatus() != "Activo") {
        	return "Empleado no autorizado.";
        }

        // Cambiar el estado del préstamo a "rechazado"
        loan.setStatus("Rechazado");

        // Registrar quién rechazó el préstamo
        loan.setRevised_by(employeer);

        // Guardar los cambios en el préstamo
        loanRepository.save(loan);

        return "Préstamo rechazado correctamente.";
    }

    // Función para aceptar un préstamo
    public String approveLoan(UUID loanId, UUID approvedById) {
        // Obtener el préstamo
        Loan loan = loanRepository.findById(loanId).orElse(null);

        if (loan == null) {
            return "Préstamo no encontrado.";
        }
        
        Employeer employeer = employeerRepository.findById(approvedById).orElse(null);
        if(employeer == null || employeer.getStatus() != "Activo") {
        	return "Empleado no autorizado.";
        }

        // Cambiar el estado del préstamo a "aprobado"
        loan.setStatus("Aprobado");

        // Registrar quién aprobó el préstamo
        
        loan.setRevised_by(employeer);

        // Aquí podrías actualizar otros campos del préstamo si es necesario, como la fecha de aprobación, etc.

        // Guardar los cambios en el préstamo
        loanRepository.save(loan);

        return "Préstamo aprobado correctamente.";
    }

    public void delete(UUID id) {
        loanRepository.deleteById(id);
    }
    
 // Función para calcular el número de años para pagar un préstamo con intereses
    public static double calculateYears(double amount, double interest, double montlyPayment) {
        if (montlyPayment <= 0) {
            throw new IllegalArgumentException("El pago mensual debe ser mayor que cero.");
        }
        
        // Calcular la tasa de interés mensual
        double interestMontly = interest / 12 / 100;
        
        // Calcular el número de pagos (n) con la fórmula de amortización
        double n = Math.log(montlyPayment / (montlyPayment - amount * interestMontly)) / Math.log(1 + interestMontly);
        
        // Calcular el número de años
        double years = n / 12;
        
        return years;
    }
}
