package com.training.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Username este obligatoriu")
    private String username;

    @NotBlank(message = "Parola este obligatorie")
    private String parola;
}
