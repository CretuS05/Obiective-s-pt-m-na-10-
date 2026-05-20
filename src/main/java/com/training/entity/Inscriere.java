package com.training.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscrierii")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscriere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curs_id", nullable = false)
    private Curs curs;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusInscriere status = StatusInscriere.ACTIVA;

    @Column(nullable = false)
    private Double nota = 0.0;

    @Column(nullable = false)
    private LocalDateTime dataInscriere = LocalDateTime.now();

    private LocalDateTime dataFinalizare;

    @Column(nullable = false)
    private Integer procent_prezenta = 0;

    private String feedback;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
}
