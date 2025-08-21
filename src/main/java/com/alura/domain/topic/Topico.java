package com.alura.domain.topic;

import com.alura.domain.topic.dto.DatosRegistroTopico;
import com.alura.domain.topic.dto.DatosActualizarTopico;
import com.alura.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Usuario autor;

    private String course;
    private boolean active = true;

    // Constructor con DTO y autor
    public Topico(DatosRegistroTopico datos, Usuario autor) {
        this.title = datos.title();
        this.message = datos.message();
        this.status = datos.status();
        this.course = datos.course();
        this.autor = autor;
        this.active = true;
    }

    // Actualizar campos desde DTO
    public void actualizarTopico(DatosActualizarTopico datos){
        if (datos.title() != null) this.title = datos.title();
        if (datos.message() != null) this.message = datos.message();
        if (datos.status() != null) this.status = datos.status();
        if (datos.curso() != null) this.course = datos.curso();
    }

    public boolean getActive() {
        return this.active;
    }

    public void desactivarTopico() {
        this.active= false;
    }

}
