package com.alura.domain.topic.interfaz;

import com.alura.domain.topic.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicoRepository extends JpaRepository<Topico, Long> {

    // Validaciones de duplicados
    boolean existsByTitleIgnoreCase(String title);
    boolean existsByMessageIgnoreCase(String message);

    // Listado paginado de tópicos activos
    Page<Topico> findAllByActiveTrue(Pageable paginacion);

    // Consulta específica para saber si un tópico está activo
    @Query("select t.active from Topico t where t.id = :idTopico")
    Boolean estaActivo(@Param("idTopico") Long idTopico);
}
