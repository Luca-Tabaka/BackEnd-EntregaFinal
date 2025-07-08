package com.proyecto.talento.carrito.service;

import com.proyecto.talento.carrito.model.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> listarPedidos();
    Optional<Pedido> obtenerPedidoPorId(Long id);
    Pedido guardarPedido(Pedido pedido);
    Pedido actualizarPedidoCompleto(Long id, Pedido pedido);
    Pedido actualizarPartePedido(Long id, Pedido pedido);
    void eliminarPedido(Long id);
}
