package com.alura.domain.topic.service;

import com.alura.domain.ValidacionException;
import com.alura.domain.topic.Topico;
import com.alura.domain.topic.dto.DatosRespuestaTopico;
import com.alura.domain.topic.dto.DatosRegistroTopico;
import com.alura.domain.topic.interfaz.ITopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de la lógica de negocio relacionada con los tópicos.
 */
@Service
public class TopicoService {

    private final ITopicoRepository topicoRepository;

    public TopicoService(ITopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    /**
     * Registra un nuevo tópico en la base de datos.
     *
     * @param datosRegistroTopico Datos de registro del tópico
     * @return DatosDetallesTopico DTO con la información completa del tópico registrado
     * @throws ValidacionException Si ya existe un tópico con el mismo título o mensaje
     */


    @Transactional
    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datosRegistroTopico) {

        // Validación de duplicados: título
        if (topicoRepository.existsByTitleIgnoreCase(datosRegistroTopico.title())) {
            throw new ValidacionException("Ya existe un tópico con este title");
        }

        // Validación de duplicados: mensaje
        if (topicoRepository.existsByMessageIgnoreCase(datosRegistroTopico.message())) {
            throw new ValidacionException("Ya existe un tópico con este message");
        }

        // Crear y guardar el tópico en la base de datos
        Topico topico = new Topico(datosRegistroTopico);
        topicoRepository.save(topico);

        // Mapear la entidad guardada a un DTO de respuesta
        return new DatosRespuestaTopico(topico);
    }
}
