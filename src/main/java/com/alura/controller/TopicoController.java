package com.alura.controller;

import com.alura.domain.ValidacionException;
import com.alura.domain.topic.dto.DatosListarTopico;
import com.alura.domain.topic.dto.DatosRegistroTopico;
import com.alura.domain.topic.dto.DatosRespuestaTopico;
import com.alura.domain.topic.interfaz.ITopicoRepository;
import com.alura.domain.topic.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;
    @Autowired
    private ITopicoRepository topicoRepository;

    /**
     * REST API POST
     * Registrar nuevo Topico
     */

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriBuilder) {

        DatosRespuestaTopico topicoRegistrado = topicoService.registrarTopico(datosRegistroTopico);

        URI url = uriBuilder.path("/topic/{id}")
                .buildAndExpand(topicoRegistrado.id())
                .toUri();

        return ResponseEntity.created(url).body(topicoRegistrado); // 201 Created
    }

    /**
     * REST API GET
     * Obtener todos los Topico
     */

    @GetMapping
    public ResponseEntity<Page<DatosListarTopico>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        var page = topicoRepository.findAllByActivoTrue(paginacion).map(DatosListarTopico::new);
        return ResponseEntity.ok(page);
    }

}
