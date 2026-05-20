package com.training.controller;

import com.training.dto.LoginRequest;
import com.training.dto.AuthResponse;
import com.training.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login-profesor")
    public ResponseEntity<AuthResponse> loginProfesor(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginProfesor(request));
    }

    @PostMapping("/login-student")
    public ResponseEntity<AuthResponse> loginStudent(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginStudent(request));
    }
}
