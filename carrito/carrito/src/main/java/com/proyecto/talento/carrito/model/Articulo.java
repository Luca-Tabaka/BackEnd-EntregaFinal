package com.proyecto.talento.carrito.model;
import jakarta.persistence.*;


@Entity
@Table(name = "articulos") 
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private String imagen;

    public Articulo() {}

    public Articulo(Long id, String nombre, String descripcion, Double precio, String imagen, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    /*getters y setters */
    /*id */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    /* nombre */
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    /* descripcion */
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /* precio */
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    /* categoria */
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    /* imagen */
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}
