package com.example.springbootpostgresqlcrud.controller;

import com.example.springbootpostgresqlcrud.DTO.Loginrequest;
import com.example.springbootpostgresqlcrud.DTO.Loginresponse;
import com.example.springbootpostgresqlcrud.DTO.Registerrequest;
import com.example.springbootpostgresqlcrud.JWT.JwtUtils;
import com.example.springbootpostgresqlcrud.model.User;
import com.example.springbootpostgresqlcrud.repository.Userrepository;
import com.example.springbootpostgresqlcrud.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class Authcontroller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Userrepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/register")
    public String register(@RequestBody Registerrequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username is already taken!";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return "User registered successfully!";
    }

    @PostMapping("/login")
    public Loginresponse login(@RequestBody Loginrequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Use the authenticated username to generate the token
        String jwt = jwtUtils.generateToken(authentication.getName());

        return new Loginresponse(jwt);
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok(authentication.getName());
    }
}
