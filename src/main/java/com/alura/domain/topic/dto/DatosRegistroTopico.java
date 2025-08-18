package com.alura.domain.topic.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(

        @NotBlank(message = "Título es obligatorio")
        String title,
        @NotBlank(message = "Mensaje es obligatorio")
        String message,
        @NotBlank(message = "Estatus es obligatorio")
        String status,
        @NotNull(message = "Curso es obligatorio")
        String course
) {
}
