package com.alura.domain.topic.service;

import com.alura.domain.ValidacionException;
import com.alura.domain.topic.Topico;
import com.alura.domain.topic.dto.DatosActualizarTopico;
import com.alura.domain.topic.dto.DatosListarTopico;
import com.alura.domain.topic.dto.DatosRegistroTopico;
import com.alura.domain.topic.dto.DatosRespuestaTopico;
import com.alura.domain.topic.interfaz.ITopicoRepository;
import com.alura.domain.usuario.Usuario;
import com.alura.domain.usuario.interfaz.IUsuarioRepository;
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
        // Validación de duplicados
        if (topicoRepository.existsByTitleIgnoreCase(datosRegistroTopico.title())) {
            throw new ValidacionException("Ya existe un tópico con este título");
        }
        if (topicoRepository.existsByMessageIgnoreCase(datosRegistroTopico.message())) {
            throw new ValidacionException("Ya existe un tópico con este mensaje");
        }

        // Buscar autor
        Usuario autor = usuarioRepository.findById(datosRegistroTopico.authorId())
                .orElseThrow(() -> new ValidacionException(
                        "Usuario no encontrado con id: " + datosRegistroTopico.authorId()));

        // Crear y guardar tópico
        Topico topico = new Topico(datosRegistroTopico, autor);
        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }

    @Transactional
    public DatosListarTopico detalleTopico(Long id) {
        // Buscar tópico por ID
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con ID: " + id));

        // Verificar si está activo
        if (!topico.getActive()) {
            throw new EntityNotFoundException("Tópico inactivo con ID: " + id);
        }

        return new DatosListarTopico(topico);
    }

    @Transactional
    public DatosRespuestaTopico actualizarTopico(Long id, DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el tópico con ID: " + id));

        topico.actualizarTopico(datosActualizarTopico);
        return new DatosRespuestaTopico(topico);
    }

    @Transactional
    public void eliminarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el tópico con ID: " + id));

        topico.desactivarTopico(); // Marca como inactivo
    }


}
