package com.training.dto;

import com.training.entity.StatusCurs;
import com.training.entity.PrioritateCurs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursDTO {
    private Long id;
    private String titlu;
    private String descriere;
    private String cod;
    private Long profesorId;
    private String profesorNume;
    private Integer locuri;
    private Integer locuriOcupate;
    private String nivel;
    private StatusCurs status;
    private PrioritateCurs prioritate;
    private Integer oreTotale;
    private Double pret;
}
