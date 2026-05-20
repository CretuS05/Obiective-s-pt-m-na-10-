package com.training.controller;

import com.training.dto.InscriereDTO;
import com.training.service.InscriereService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscrierii")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class InscriereController {
    private final InscriereService inscriereService;

    @PostMapping("/inscriere")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<InscriereDTO> inscriudeStudent(@RequestParam Long studentId, @RequestParam Long cursId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscriereService.inscriudeStudent(studentId, cursId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<InscriereDTO> updateInscriere(@PathVariable Long id, @RequestBody InscriereDTO inscriereDTO) {
        return ResponseEntity.ok(inscriereService.updateInscriere(id, inscriereDTO));
    }

    @DeleteMapping("/{inscriereId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Void> desinscriereStudent(@PathVariable Long inscriereId) {
        inscriereService.desinscriereStudent(inscriereId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<InscriereDTO>> getStudentInscrierii(@PathVariable Long studentId) {
        return ResponseEntity.ok(inscriereService.getStudentInscrierii(studentId));
    }

    @GetMapping("/curs/{cursId}")
    public ResponseEntity<List<InscriereDTO>> getCursInscrierii(@PathVariable Long cursId) {
        return ResponseEntity.ok(inscriereService.getCursInscrierii(cursId));
    }
}
