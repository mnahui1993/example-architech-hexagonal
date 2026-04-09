package org.marco.infraestructure.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteCreateRequest(@NotBlank(message = "El nombre es obligatorio") String nombre
    , @NotBlank(message = "El email es obligatorio")
                                   @Email(message = "Email inválido") String email) {

}
