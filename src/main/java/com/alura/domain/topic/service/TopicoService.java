package com.alura.domain.topic.service;

import com.alura.domain.ValidacionException;
import com.alura.domain.topic.Topico;
import com.alura.domain.topic.dto.DatosActualizarTopico;
import com.alura.domain.topic.dto.DatosRegistroTopico;
import com.alura.domain.topic.dto.DatosRespuestaTopico;
import com.alura.domain.topic.interfaz.ITopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    private final ITopicoRepository topicoRepository;

    public TopicoService(ITopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    //Registrar Topico
    @Transactional
    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datosRegistroTopico) {
        if (topicoRepository.existsByTitleIgnoreCase(datosRegistroTopico.title())) {
            throw new ValidacionException("Ya existe un tópico con este title");
        }
        if (topicoRepository.existsByMessageIgnoreCase(datosRegistroTopico.message())) {
            throw new ValidacionException("Ya existe un tópico con este message");
        }
        Topico topico = new Topico(datosRegistroTopico);
        topicoRepository.save(topico);
        return new DatosRespuestaTopico(topico);
    }

    //Actualizar Topico
    @Transactional
    public DatosRespuestaTopico actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
        Topico topico;
        try {
            topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("No se encontró el tópico con ID: " + datosActualizarTopico.id());
        }
        topico.actualizarTopico(datosActualizarTopico);
        return new DatosRespuestaTopico(topico);
    }
}
