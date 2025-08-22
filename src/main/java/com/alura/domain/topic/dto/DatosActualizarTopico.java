package com.alura.domain.topic.dto;
import com.alura.domain.topic.Status;
import com.alura.domain.topic.Topico;

public record DatosActualizarTopico(
        Long id,           // agregamos el ID aquí
        String title,
        String message,
        Status status,
        String curso,
        String autorUsername  // nuevo campo para el autor

) {


    // Método útil para crear un nuevo DTO con un ID actualizado
    public DatosActualizarTopico(Topico topico) {
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
