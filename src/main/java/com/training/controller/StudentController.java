package com.training.controller;

import com.training.dto.StudentDTO;
import com.training.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studenti")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<List<StudentDTO>> getAllStudenti() {
        return ResponseEntity.ok(studentService.getAllStudenti());
    }

    @GetMapping("/activi")
    public ResponseEntity<List<StudentDTO>> getActivStudenti() {
        return ResponseEntity.ok(studentService.getActivStudenti());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
