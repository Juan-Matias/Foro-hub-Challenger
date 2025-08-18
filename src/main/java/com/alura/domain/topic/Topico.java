package com.alura.domain.topic;

import com.alura.domain.topic.dto.DatosActualizarTopico;
import com.alura.domain.topic.dto.DatosRegistroTopico;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String message;

    @Column(name = "date", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "status")
    private String status;

    @Column(name = "author_id")
    private Long autor;

    @Column(nullable = false)
    private String course;

    @Column(nullable = false)
    private boolean active = true;

    // Constructor que recibe el DTO
    public Topico(DatosRegistroTopico datos) {
        this.title = datos.title();
        this.message = datos.message();
        this.status = datos.status();
        this.course = datos.course();
        this.active = true;
    }

    public void actualizarTopico(DatosActualizarTopico datos){
        if (datos.title() !=null){
            this.title= datos.title();
        }
        if (datos.message() != null){
            this.message=datos.message();
        }
        if (datos.status() != null){
            this.status = String.valueOf(datos.status());
        }
        if (datos.curso() != null){
            this.course=datos.curso();
        }
    }

}
