package com.alura.domain.topic.dto;
import com.alura.domain.topic.Topico;
import java.time.LocalDateTime;

public record DatosListarTopico(
        Long id,
        String title,
        String message,
        LocalDateTime fechaCreacion,
        String estatus,
        Long autor,
        String course
) {
    public DatosListarTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                topico.getFechaCreacion(),
                topico.getEstatus(),
                topico.getAutor(),
                topico.getCourse()
        );
    }
}
