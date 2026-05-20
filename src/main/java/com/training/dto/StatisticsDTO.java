package com.training.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDTO {
    private long totalStudenti;
    private long studentiActivi;
    private long totalCursuri;
    private long cursoriActivi;
    private long totalInscriieri;
    private long inscrieriActive;
    private double procent_ocupare;
    private long totalProfesori;
}
