package com.alura.controller;

import com.alura.domain.topic.Topico;
import com.alura.domain.topic.dto.DatosActualizarTopico;
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
     * Crear un nuevo Topico
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
    public ResponseEntity<Page<DatosListarTopico>> listarTopico(
            @PageableDefault(size = 10, sort = {"title"}) Pageable paginacion) {

        var page = topicoRepository.findAllByActiveTrue(paginacion)
                .map(DatosListarTopico::new);
        return ResponseEntity.ok(page);
    }

    /**
     * REST API PUT
     * Actualizar un Topico
     */


    // ✅ Explicación:
    // 1- @PathVariable Long id → captura el ID del tópico desde la URL /topic/{id}.
    // 2- findById(id) → busca en la base de datos.
    // 3- isPresent() → verifica si el tópico existe.
    // 4- actualizarTopico(datosActualizarTopico) → actualiza solo los campos no nulos.
    // 5- save(topico) → persiste los cambios.
    // 6- Devuelve 200 OK con el objeto actualizado o 404 Not Found si no existe.

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

        // Buscar el tópico por ID
        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.actualizarTopico(datosActualizarTopico);  // Actualiza campos del DTO
            topicoRepository.save(topico);                    // Guarda cambios
            return ResponseEntity.ok(new DatosRespuestaTopico(topico));
        } else {
            return ResponseEntity.notFound().build();         // 404 si no existe
        }
    }


}


