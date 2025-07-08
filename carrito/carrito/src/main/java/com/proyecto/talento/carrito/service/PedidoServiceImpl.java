package com.proyecto.talento.carrito.service;

import com.proyecto.talento.carrito.model.Pedido;
import com.proyecto.talento.carrito.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarPedidoCompleto(Long id, Pedido pedido) {
        pedido.setId(id);
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarPartePedido(Long id, Pedido pedido) {
        Optional<Pedido> pedidoExistenteOpt = pedidoRepository.findById(id);
        if (pedidoExistenteOpt.isEmpty()) {
            throw new RuntimeException("Pedido no encontrado");
        }
        Pedido pedidoExistente = pedidoExistenteOpt.get();

        if (pedido.getFechaPedido() != null) {
            pedidoExistente.setFechaPedido(pedido.getFechaPedido());
        }
        if (pedido.getFechaEntrega() != null) {
            pedidoExistente.setFechaEntrega(pedido.getFechaEntrega());
        }
        if (pedido.getEstado() != null) {
            pedidoExistente.setEstado(pedido.getEstado());
        }

        return pedidoRepository.save(pedidoExistente);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
