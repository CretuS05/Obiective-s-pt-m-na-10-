package com.training.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;

@Entity
@Table(name = "programe_cursuri")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramCurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curs_id", nullable = false)
    private Curs curs;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek ziuaSaptamanii;

    @Column(nullable = false)
    private LocalTime oraStart;

    @Column(nullable = false)
    private LocalTime oraFinal;

    @Column(nullable = false)
    private String locatie;

    @Column(nullable = false)
    private LocalDate dataStart;

    @Column(nullable = false)
    private LocalDate dataFinal;

    @Column(nullable = false)
    private Integer durata; // in minute

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
}
