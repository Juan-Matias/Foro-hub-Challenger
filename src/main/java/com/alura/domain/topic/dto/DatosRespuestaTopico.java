package com.alura.domain.topic.dto;
import jakarta.validation.constraints.NotBlank;

public record DatosRespuestaTopico(
        Long id,
        @NotBlank String title,
        @NotBlank String message,
        String estatus,
        String course
) {
}
