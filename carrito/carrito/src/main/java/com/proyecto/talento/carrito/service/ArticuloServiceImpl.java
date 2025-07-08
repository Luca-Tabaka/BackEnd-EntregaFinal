package com.proyecto.talento.carrito.service;
import com.proyecto.talento.carrito.model.Articulo;
import com.proyecto.talento.carrito.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    private final ArticuloRepository articuloRepository;

    @Autowired
    public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public List<Articulo> listarArticulos() {
        return articuloRepository.findAll();
    }
    
    public Optional<Articulo> obtenerArticuloPorId(Long id) {
        return articuloRepository.findById(id);
    }

    public Articulo guardarArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    /* Actualiza el articulo por partes */
    public Articulo actualizarParteArticulo(Long id, Articulo articulo) {
        Optional<Articulo> articuloExistenteOpt = articuloRepository.findById(id);
        if (articuloExistenteOpt.isEmpty()) {
            throw new RuntimeException("Artículo no encontrado");
        }
        Articulo articuloExistente = articuloExistenteOpt.get();

        if (articulo.getNombre() != null) {
            articuloExistente.setNombre(articulo.getNombre());
        }
        if (articulo.getDescripcion() != null) {
            articuloExistente.setDescripcion(articulo.getDescripcion());
        }
        if (articulo.getCategoria() != null) {
            articuloExistente.setCategoria(articulo.getCategoria());
        }
        if (articulo.getPrecio() != null) {
            articuloExistente.setPrecio(articulo.getPrecio());
        }
        if (articulo.getImagen() != null) {
            articuloExistente.setImagen(articulo.getImagen());
        }

        return articuloRepository.save(articuloExistente);
    }

    public Articulo actualizarArticuloCompleto(Long id, Articulo articulo) {
        articulo.setId(id);
        return articuloRepository.save(articulo);
    }

    public void eliminarArticulo(Long id) {
        articuloRepository.deleteById(id);
    }
}
