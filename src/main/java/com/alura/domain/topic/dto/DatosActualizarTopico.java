package com.alura.domain.topic.dto;

import com.alura.domain.topic.Status;

public record DatosActualizarTopico(
        Long id,           // agregamos el ID aquí
        String title,
        String message,
        Status status,
        String curso
) {

    // Método útil para crear un nuevo DTO con un ID actualizado
    public DatosActualizarTopico id(Long id) {
        return new DatosActualizarTopico(
                id,
                this.title,
                this.message,
                this.status,
                this.curso
        );
    }
}
