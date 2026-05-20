package com.training.controller;

import com.training.dto.StatisticsDTO;
import com.training.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESOR', 'STUDENT')")
    public ResponseEntity<StatisticsDTO> getStatistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }
}
