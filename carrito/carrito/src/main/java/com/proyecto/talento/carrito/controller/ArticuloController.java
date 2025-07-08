package com.proyecto.talento.carrito.controller;

import com.proyecto.talento.carrito.model.Articulo;
import com.proyecto.talento.carrito.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    private final ArticuloService articuloService;

    @Autowired
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    public List<Articulo> listar() {
        return articuloService.listarArticulos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Articulo crear(@RequestBody Articulo articulo) {
        return articuloService.guardarArticulo(articulo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> actualizar(@PathVariable Long id, @RequestBody Articulo articulo) {
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(articuloService.actualizarArticuloCompleto(id, articulo));
    }

    /* Cambio el put por el patch */
    @PatchMapping("/{id}")
    public ResponseEntity<Articulo> actualizarParte(@PathVariable Long id, @RequestBody Articulo articulo) {
        Optional<Articulo> articuloExistente = articuloService.obtenerArticuloPorId(id);
        if (articuloExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Articulo actualizado = articuloService.actualizarParteArticulo(id, articulo);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        articuloService.eliminarArticulo(id);
        return ResponseEntity.noContent().build();
    }
}
