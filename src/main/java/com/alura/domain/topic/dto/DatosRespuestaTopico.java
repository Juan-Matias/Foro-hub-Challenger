package com.alura.domain.topic.dto;

import com.alura.domain.topic.Status;
import com.alura.domain.topic.Topico;

public record DatosRespuestaTopico(
        Long id,
        String title,
        String message,
        Status status,
        String course,
        String autorUsername  // nuevo campo para el autor
) {
    public DatosRespuestaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                topico.getStatus(),
                topico.getCourse(),
                topico.getAutor() != null ? topico.getAutor().getUsername() : null
        );
    }
}
