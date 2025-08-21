package com.alura.domain.topic.service;

import com.alura.domain.ValidacionException;
import com.alura.domain.topic.Topico;
import com.alura.domain.topic.dto.DatosActualizarTopico;
import com.alura.domain.topic.dto.DatosRegistroTopico;
import com.alura.domain.topic.dto.DatosRespuestaTopico;
import com.alura.domain.topic.interfaz.ITopicoRepository;
import com.alura.domain.usuario.Usuario;
import com.alura.domain.usuario.interfaz.IUsuarioRepository; // Necesitas este repo
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private ITopicoRepository topicoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Transactional
    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datosRegistroTopico) {
        if (topicoRepository.existsByTitleIgnoreCase(datosRegistroTopico.title())) {
            throw new ValidacionException("Ya existe un tópico con este title");
        }
        if (topicoRepository.existsByMessageIgnoreCase(datosRegistroTopico.message())) {
            throw new ValidacionException("Ya existe un tópico con este message");
        }

        Usuario autor = usuarioRepository.findById(datosRegistroTopico.authorId())
                .orElseThrow(() -> new ValidacionException("Usuario no encontrado con id: " + datosRegistroTopico.authorId()));

        Topico topico = new Topico(datosRegistroTopico, autor);
        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }

    @Transactional
    public DatosRespuestaTopico actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.findById(datosActualizarTopico.id())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el tópico con ID: " + datosActualizarTopico.id()));

        topico.actualizarTopico(datosActualizarTopico);
        return new DatosRespuestaTopico(topico);
    }
}
