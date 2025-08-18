package com.alura.domain.topic.dto;

import com.alura.domain.topic.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopico(
    @NotNull
    Long id,
    String title,
    String message,
    Status status,

    @NotNull
    Long usuario_Id,
    String curso,
    LocalDateTime date
) {
    }