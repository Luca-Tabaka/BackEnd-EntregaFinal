package com.proyecto.talento.carrito.service;

import com.proyecto.talento.carrito.model.Articulo;
import java.util.List;
import java.util.Optional;


public interface ArticuloService {
    List<Articulo> listarArticulos();
    Optional<Articulo> obtenerArticuloPorId(Long id);
    Articulo guardarArticulo(Articulo articulo);
    Articulo actualizarArticuloCompleto(Long id, Articulo articulo);
    Articulo actualizarParteArticulo(Long id, Articulo articulo);
    void eliminarArticulo(Long id);
}
