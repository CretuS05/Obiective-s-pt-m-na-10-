package com.training.controller;

import com.training.dto.CursDTO;
import com.training.service.CursService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursuri")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CursController {
    private final CursService cursService;

    @PostMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<CursDTO> createCurs(@RequestBody CursDTO cursDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursService.createCurs(cursDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<CursDTO> updateCurs(@PathVariable Long id, @RequestBody CursDTO cursDTO) {
        return ResponseEntity.ok(cursService.updateCurs(id, cursDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursDTO> getCurs(@PathVariable Long id) {
        return ResponseEntity.ok(cursService.getCurs(id));
    }

    @GetMapping
    public ResponseEntity<List<CursDTO>> getAllCursuri() {
        return ResponseEntity.ok(cursService.getAllCursuri());
    }

    @GetMapping("/activi")
    public ResponseEntity<List<CursDTO>> getActiveCursuri() {
        return ResponseEntity.ok(cursService.getActiveCursuri());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CursDTO>> searchCursuri(@RequestParam String keyword) {
        return ResponseEntity.ok(cursService.searchCursuri(keyword));
    }

    @GetMapping("/profesor/{profesorId}")
    public ResponseEntity<List<CursDTO>> getCursuriByProfesor(@PathVariable Long profesorId) {
        return ResponseEntity.ok(cursService.getCursuriByProfesor(profesorId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> deleteCurs(@PathVariable Long id) {
        cursService.deleteCurs(id);
        return ResponseEntity.noContent().build();
    }
}
