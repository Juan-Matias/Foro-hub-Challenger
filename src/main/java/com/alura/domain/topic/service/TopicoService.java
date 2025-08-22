package com.alura.domain.topic.service;

import com.alura.domain.ValidacionException;
import com.alura.domain.topic.Topico;
import com.alura.domain.topic.dto.*;
import com.alura.domain.topic.interfaz.ITopicoRepository;
import com.alura.domain.usuario.Usuario;
import com.alura.domain.usuario.interfaz.IUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private ITopicoRepository topicoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    // Crear tópico (POST)
    @Transactional
    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datosRegistroTopico) {
        if (topicoRepository.existsByTitleIgnoreCase(datosRegistroTopico.title())) {
            throw new ValidacionException("Ya existe un tópico con este título");
        }
        if (topicoRepository.existsByMessageIgnoreCase(datosRegistroTopico.message())) {
            throw new ValidacionException("Ya existe un tópico con este mensaje");
        }

        Usuario autor = usuarioRepository.findById(datosRegistroTopico.authorId())
                .orElseThrow(() -> new ValidacionException(
                        "Usuario no encontrado con id: " + datosRegistroTopico.authorId()));

        Topico topico = new Topico(datosRegistroTopico, autor);
        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }

    // Obtener todos los tópicos activos (GET /topic)
    @Transactional
    public List<DatosListarTopico> listarTopicos() {
        return topicoRepository.findAll().stream()
                .filter(Topico::getActive) // solo activos
                .map(DatosListarTopico::new)
                .toList();
    }

    // Obtener detalle tópico (GET /topic/{id})
    @Transactional
    public DatosListarTopico detalleTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con ID: " + id));

        if (!topico.getActive()) {
            throw new EntityNotFoundException("Tópico inactivo con ID: " + id);
        }

        return new DatosListarTopico(topico);
    }

    // Actualizar tópico (PUT /topic/{id})
    @Transactional
    public DatosActualizarTopico actualizarTopico(Long id, DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el tópico con ID: " + id));

        topico.actualizarTopico(datosActualizarTopico);
        return new DatosActualizarTopico(topico);
    }

    // Eliminar tópico (DELETE /topic/{id})
    @Transactional
    public void eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidacionException("No se encontró el tópico con ID: " + id);
        }

        Topico topico = topicoRepository.findById(id).get(); // seguro porque ya validamos existencia
        topico.desactivarTopico(); // soft delete
    }
}
