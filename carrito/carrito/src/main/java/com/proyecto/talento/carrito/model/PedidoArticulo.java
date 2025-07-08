package com.proyecto.talento.carrito.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_articulo")
public class PedidoArticulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "articulo_id", nullable = false)
    private Articulo articulo;

    private int cantidad;

    
    // Getters y setters
    /* id */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    /* Relación con Pedido */
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    /* Relación con Articulo */
    public Articulo getArticulo() { return articulo; }
    public void setArticulo(Articulo articulo) { this.articulo = articulo; }

    /* Cantidad del artículo en el pedido */
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
