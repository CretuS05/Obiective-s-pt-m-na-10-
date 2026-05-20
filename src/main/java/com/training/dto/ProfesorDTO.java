package com.training.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDTO {
    private Long id;
    private String nume;
    private String prenume;
    private String email;
    private String username;
    private String specialitate;
    private String telefon;
    private Boolean activ;
}
