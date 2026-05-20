package com.training.service;

import com.training.dto.LoginRequest;
import com.training.dto.AuthResponse;
import com.training.entity.Profesor;
import com.training.entity.Student;
import com.training.repository.ProfesorRepository;
import com.training.repository.StudentRepository;
import com.training.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final ProfesorRepository profesorRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse loginProfesor(LoginRequest request) {
        Profesor profesor = profesorRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Profesor nu gasit"));

        if (!passwordEncoder.matches(request.getParola(), profesor.getParola())) {
            throw new RuntimeException("Parola invalida");
        }

        String token = jwtUtil.generateToken(profesor.getUsername(), "PROFESOR");
        return new AuthResponse(token, profesor.getId(), profesor.getUsername(),
                profesor.getEmail(), "PROFESOR", profesor.getNume() + " " + profesor.getPrenume());
    }

    public AuthResponse loginStudent(LoginRequest request) {
        Student student = studentRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Student nu gasit"));

        if (!passwordEncoder.matches(request.getParola(), student.getParola())) {
            throw new RuntimeException("Parola invalida");
        }

        String token = jwtUtil.generateToken(student.getUsername(), "STUDENT");
        return new AuthResponse(token, student.getId(), student.getUsername(),
                student.getEmail(), "STUDENT", student.getNume() + " " + student.getPrenume());
    }
}
