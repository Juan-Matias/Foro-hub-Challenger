package com.alura.domain.topic.dto;

import com.alura.domain.topic.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(

        @NotBlank(message = "Título es obligatorio")
        String title,

        @NotBlank(message = "Mensaje es obligatorio")
        String message,

        @NotNull(message = "Estatus es obligatorio")
        Status status,

        @NotBlank(message = "Curso es obligatorio")
        String course,

        @NotNull(message = "Autor es obligatorio")
        Long authorId  // Nuevo campo para pasar el usuario
) {}
