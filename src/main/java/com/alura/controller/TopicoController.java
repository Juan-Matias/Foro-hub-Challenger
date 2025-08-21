package com.alura.controller;

import com.alura.domain.topic.Topico;
import com.alura.domain.topic.dto.*;
import com.alura.domain.topic.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    /**
     * REST API POST → Crear un nuevo Tópico
     */
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

    /**
     * REST API GET → Obtener detalle de un Tópico por ID
     */
    @GetMapping("/topicos/{id}")
    public ResponseEntity<DatosListarTopico> detalleTopico(@PathVariable Long id) {
        DatosListarTopico dto = topicoService.detalleTopico(id);

        // Solo devolver si el tópico está activo
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    /**
     * REST API PUT → Actualizar un Tópico por ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

        DatosRespuestaTopico dto = topicoService.actualizarTopico(id, datosActualizarTopico);
        return ResponseEntity.ok(dto);
    }

    /**
     * REST API DELETE → Eliminar un Tópico por ID
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
