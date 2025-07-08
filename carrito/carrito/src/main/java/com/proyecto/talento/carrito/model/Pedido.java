package com.proyecto.talento.carrito.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fechaPedido;
    private String fechaEntrega;
    private String estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoArticulo> pedidoArticulos = new HashSet<>();

    public Pedido() {}

    public Pedido(Long id, String fechaPedido, String fechaEntrega, String estado) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    // Getters y setters

    /* id */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    /* Fecha del pedido */
    public String getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(String fechaPedido) { this.fechaPedido = fechaPedido; }

    /* Fecha de entrega */
    public String getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(String fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    /* Estado del pedido */
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    /* Relación con PedidoArticulo */
    
    public Set<PedidoArticulo> getPedidoArticulos() { return pedidoArticulos; }
    public void setPedidoArticulos(Set<PedidoArticulo> pedidoArticulos) {
        this.pedidoArticulos = pedidoArticulos;
    }

    public void agregarPedidoArticulo(PedidoArticulo pedidoArticulo) {
        pedidoArticulos.add(pedidoArticulo);
        pedidoArticulo.setPedido(this);
    }

    public void eliminarPedidoArticulo(PedidoArticulo pedidoArticulo) {
        pedidoArticulos.remove(pedidoArticulo);
        pedidoArticulo.setPedido(null);
    }
}
