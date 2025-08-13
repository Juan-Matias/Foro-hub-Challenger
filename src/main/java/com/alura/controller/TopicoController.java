package com.alura.controller;

import com.alura.domain.topic.Topico;
import com.alura.domain.topic.dto.DatosRespuestaTopico;
import com.alura.domain.topic.interfaz.ITopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
public class TopicoController {

    @Autowired
    private ITopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopic
            (
                    @RequestBody @Valid
                    DatosRespuestaTopico datosRespuestaTopic,
                    UriComponentsBuilder uriComponentsBuilder
            ) {
        Topico topico = topicoRepository.save(new Topico(datosRespuestaTopic));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                topico.getEstatus(),
                topico.getCourse()
        );

        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }
}
