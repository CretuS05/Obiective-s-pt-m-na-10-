package com.training.dto;

import com.training.entity.StatusInscriere;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriereDTO {
    private Long id;
    private Long studentId;
    private String studentNume;
    private Long cursId;
    private String cursTitlu;
    private StatusInscriere status;
    private Double nota;
    private LocalDateTime dataInscriere;
    private Integer procent_prezenta;
}
