package com.training.dto;

import com.training.entity.StatusStudent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String nume;
    private String prenume;
    private String email;
    private String username;
    private String telefon;
    private String nivelelStudiu;
    private StatusStudent status;
}
