package com.dwf.bank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dwf.bank.models.Branch;
import com.dwf.bank.repositories.BranchRepository;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;
    
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch get(Integer id) {
        return branchRepository.findById(id).orElse(null);
    }

    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch update(Integer id, Branch branch) {
        if (branchRepository.existsById(id)) {
            branch.setId(id);
            return branchRepository.save(branch);
        }
        return null;
    }

    public void delete(Integer id) {
        branchRepository.deleteById(id);
    }
}
