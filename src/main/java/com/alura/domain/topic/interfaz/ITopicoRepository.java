package com.alura.domain.topic.interfaz;
import com.alura.domain.topic.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTitleIgnoreCase(String title);
    boolean existsByMessageIgnoreCase(String message);

    Page<Topico> findAllByActivoTrue(Pageable paginacion);
}
