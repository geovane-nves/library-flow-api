package com.Project.LibraryFlow.auth.controller;

import com.Project.LibraryFlow.auth.dtos.AuthResponseDTO;
import com.Project.LibraryFlow.auth.dtos.LoginDTO;
import com.Project.LibraryFlow.auth.dtos.RegisterDTO;
import com.Project.LibraryFlow.auth.dtos.RegisterResponseDTO;
import com.Project.LibraryFlow.security.services.JwtService;
import com.Project.LibraryFlow.user.entities.User;
import com.Project.LibraryFlow.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(dto));
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginDTO dto) {
        User user = userService.findByEmail(dto.email());
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponseDTO(token);
    }
}