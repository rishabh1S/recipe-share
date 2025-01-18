package com.springboot.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.recipe.configuration.JwtUtils;
import com.springboot.recipe.model.LoginDTO;
import com.springboot.recipe.model.User;
import com.springboot.recipe.service.UserService;

@RestController
@RequestMapping("/api/users")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.createUser(user);
            return ResponseEntity.status(201).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if (authentication.isAuthenticated()) {
                User myUser = userService.findByUsername(user.getUsername());
                if (myUser == null) {
                    throw new UsernameNotFoundException("User not found");
                }
                LoginDTO userDTO = new LoginDTO();
                userDTO.setId(myUser.getId());
                userDTO.setEmail(myUser.getEmail());
                userDTO.setUsername(myUser.getUsername());
                userDTO.setUserRole(myUser.getUserRole());
                userDTO.setToken(jwtService.GenerateToken(myUser.getUsername()));

                return ResponseEntity.status(200).body(userDTO);
            } else {
                throw new BadCredentialsException("Invalid credentials");
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials: " + e.getMessage());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("User not found: " + e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseEntity.status(500).body("Error logging in: " + e.getMessage());
        }
    }

}
