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
    public DatosListarTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                topico.getFechaCreacion(),
                topico.getStatus().name(), // si status es un enum
                topico.getAutor().getId(), // si autor es entidad Usuario
                topico.getCourse()
        );
    }
}
