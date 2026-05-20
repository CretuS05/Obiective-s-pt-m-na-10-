package com.training.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursuri")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titlu;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descriere;

    @Column(nullable = false, unique = true)
    private String cod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    @Column(nullable = false)
    private Integer locuri;

    @Column(nullable = false)
    private Integer locuriOcupate = 0;

    @Column(nullable = false)
    private String nivel; // Incepator, Intermediar, Avansat

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCurs status = StatusCurs.ACTIV;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrioritateCurs prioritate = PrioritateCurs.NORMALA;

    @Column(nullable = false)
    private Integer oreTotale;

    private Double pret;

    @OneToMany(mappedBy = "curs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscriere> inscrierii = new ArrayList<>();

    @OneToMany(mappedBy = "curs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramCurs> programe = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
}
