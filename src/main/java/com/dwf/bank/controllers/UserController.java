package com.dwf.bank.controllers;

import com.dwf.bank.dto.LoginDto;
import com.dwf.bank.dto.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dwf.bank.models.User;
import com.dwf.bank.security.JWTUtil;
import com.dwf.bank.services.UserService;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JWTUtil jwtUtil;
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
    	User user = userService.get(id);
    	return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user, @RequestParam(required = false) UUID createdBy) {
        User savedUser = userService.save(user, createdBy);
        return ResponseEntity.ok("Usuario registrado con ID: " + savedUser.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginDto loginDto) throws AuthenticationException {
        User user = userService.login(loginDto.getUsername(), loginDto.getPassword());
        if (user != null) {
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole().toString());
            return ResponseEntity.ok(new LoginResponseDto(token, user.getRole().name()));
        } else {
            throw new AuthenticationException("Credenciales invalidas!!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok("Usuario actualizado con ID: " + updatedUser.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.ok("Usuario desactivado con ID: " + id);
    }
}
