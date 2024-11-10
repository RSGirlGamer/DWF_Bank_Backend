package com.dwf.bank.controllers;

import com.dwf.bank.models.Branch;
import com.dwf.bank.services.BranchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> addBranch(@RequestBody Branch branch) {
        Branch savedBranch = branchService.save(branch);
        return ResponseEntity.ok("Sucursal agregada con ID: " + savedBranch.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBranch(@PathVariable Integer id, @RequestBody Branch branch) {
        Branch updatedBranch = branchService.update(id, branch);
        return ResponseEntity.ok("Sucursal actualizada con ID: " + updatedBranch.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBranch(@PathVariable Integer id) {
        branchService.delete(id);
        return ResponseEntity.ok("Sucursal eliminada con ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Integer id) {
        Branch branch = branchService.get(id);
        return ResponseEntity.ok(branch);
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Branch>> getAllBranches() {
//        List<Branch> branches = branchService.getAllBranches();
//        return ResponseEntity.ok(branches);
//    }
}
