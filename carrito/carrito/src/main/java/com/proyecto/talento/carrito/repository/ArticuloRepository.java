package com.proyecto.talento.carrito.repository;
import com.proyecto.talento.carrito.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    /* nombre */
    List<Articulo> findByNombre(String nombre);
    List<Articulo> findByNombreContaining(String texto);
    List<Articulo> findByNombreIgnoreCase(String nombre);
    /* precio */
    List<Articulo> findByPrecioGreaterThan(Double precio);
    List<Articulo> findByPrecioBetween(Double min, Double max);
    List<Articulo> findAllByOrderByPrecioAsc();
    /* nombre y precio */
    List<Articulo> findByNombreAndPrecioGreaterThan(String nombre, Double precio);

    /* categoria */
    List<Articulo> findByCategoria(String categoria);
    List<Articulo> findByCategoriaContaining(String texto);
    List<Articulo> findByCategoriaIgnoreCase(String categoria);

    /* categoria y precio */
    List<Articulo> findByCategoriaAndPrecioGreaterThan(String categoria, Double precio);
}
