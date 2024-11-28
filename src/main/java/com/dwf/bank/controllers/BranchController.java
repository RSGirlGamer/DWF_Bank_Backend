package com.dwf.bank.controllers;

import com.dwf.bank.models.Branch;
import com.dwf.bank.services.BranchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dwf.bank.util.RolePermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    @PreAuthorize(RolePermissions.SOLO_GERENTES)
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }
    
    @PostMapping("/add")
    @PreAuthorize(RolePermissions.SOLO_GERENTES)
    public ResponseEntity<Map<String, String>> addBranch(@RequestBody Branch branch) {
        Branch savedBranch = branchService.save(branch);
        Map<String,String> response = new HashMap<>();
        response.put("Sucursal", ""+branch.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize(RolePermissions.SOLO_GERENTES)
    public ResponseEntity<Map<String, String>> updateBranch(@PathVariable Integer id, @RequestBody Branch branch) {
        Branch updatedBranch = branchService.update(id, branch);
        Map<String, String> response = new HashMap<>();
        response.put("branch", ""+branch.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(RolePermissions.SOLO_GERENTES)
    public ResponseEntity<Map<String, String>> deleteBranch(@PathVariable Integer id) {
        branchService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("branch", ""+id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize(RolePermissions.SOLO_GERENTES)
    public ResponseEntity<Map<String, String>> getBranchById(@PathVariable Integer id) {
        Branch branch = branchService.get(id);
        Map<String, String> response = new HashMap<>();
        response.put("branch", ""+id);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Branch>> getAllBranches() {
//        List<Branch> branches = branchService.getAllBranches();
//        return ResponseEntity.ok(branches);
//    }
}
