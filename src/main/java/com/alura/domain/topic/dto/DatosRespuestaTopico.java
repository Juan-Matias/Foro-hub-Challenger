package com.alura.domain.topic.dto;
import com.alura.domain.topic.Topico;

public record DatosRespuestaTopico(
        Long id,
        String title,
        String message,
        String status,
        String course
) {
    public DatosRespuestaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                topico.getStatus(),
                topico.getCourse());
    }
}
