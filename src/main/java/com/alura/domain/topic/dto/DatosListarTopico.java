package com.alura.domain.topic.dto;
import com.alura.domain.topic.Topico;
import java.time.LocalDateTime;

public record DatosListarTopico(
        Long id,
        String title,
        String message,
        LocalDateTime fechaCreacion,
        String status,
        Long autor,
        String course
) {
}
