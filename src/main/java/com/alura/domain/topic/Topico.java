package com.alura.domain.topic;

import com.alura.domain.topic.dto.DatosRespuestaTopico;
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
    private String estatus;

    @Column(name = "author_id")
    private Long autor;

    @Column(nullable = false)
    private String course;

    @Column(nullable = false)
    private boolean active = true;

    // Constructor que recibe el DTO
    public Topico(DatosRespuestaTopico dto) {
        this.title = dto.title();
        this.message = dto.message();
        this.estatus = dto.estatus();
        this.course = dto.course();
        this.active = true;
    }

}
