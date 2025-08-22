package com.alura.controller;

import com.alura.domain.topic.dto.*;
import com.alura.domain.topic.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Crear tópico (POST)
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriBuilder) {

        DatosRespuestaTopico topicoRegistrado = topicoService.registrarTopico(datosRegistroTopico);

        URI url = uriBuilder.path("/topic/{id}")
                .buildAndExpand(topicoRegistrado.id())
                .toUri();

        return ResponseEntity.created(url).body(topicoRegistrado);
    }

    // Listar todos los tópicos (GET /topic)
    @GetMapping
    public ResponseEntity<List<DatosListarTopico>> listarTopicos() {
        return ResponseEntity.ok(topicoService.listarTopicos());
    }

    // Obtener detalle por ID (GET /topic/{id})
    @GetMapping("/{id}")
    public ResponseEntity<DatosListarTopico> detalleTopico(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.detalleTopico(id));
    }

    // Actualizar tópico (PUT /topic/{id})
    @PutMapping("/{id}")
    public ResponseEntity<DatosActualizarTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

        return ResponseEntity.ok(topicoService.actualizarTopico(id, datosActualizarTopico));
    }

    // Eliminar tópico (DELETE /topic/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
