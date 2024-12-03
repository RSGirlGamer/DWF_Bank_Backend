package com.dwf.bank.controllers;

import com.dwf.bank.dto.LoginDto;
import com.dwf.bank.dto.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dwf.bank.models.User;
import com.dwf.bank.security.JWTUtil;
import com.dwf.bank.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.AuthenticationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Map<String, String>> getUserById(@PathVariable UUID id){
    	User user = userService.get(id);
    	Map<String, String> response = new HashMap<>();
    	response.put("user", ""+id);
        response.put("username", user.getUsername());
    	return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody User user, @RequestParam(required = false) UUID createdBy) {
        User savedUser = userService.save(user, createdBy);
        Map<String, String> response = new HashMap<>();
        response.put("user", ""+user.getId());
        response.put("dui", user.getDui());
        return ResponseEntity.ok(response);
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
    /*@PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logoutUser(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> responseMessage = new HashMap<>();
        request.getSession().invalidate();
        Cookie jwtCookie = new Cookie("JWT", null); 
        
        jwtCookie.setHttpOnly(true);  
        jwtCookie.setSecure(true);  
        jwtCookie.setPath("/"); 
        jwtCookie.setMaxAge(0);
        
        responseMessage.put("message", "Sesión cerrada exitosamente.");
        return ResponseEntity.ok(responseMessage);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable UUID id, @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        Map<String, String> response = new HashMap<>();
        response.put("user", ""+updatedUser.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable UUID id) {
        userService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("usuario", ""+id);
        return ResponseEntity.ok(response);
    }
}
