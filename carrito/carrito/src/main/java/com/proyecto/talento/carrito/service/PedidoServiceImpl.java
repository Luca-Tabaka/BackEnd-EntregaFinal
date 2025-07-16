package com.proyecto.talento.carrito.service;

import com.proyecto.talento.carrito.model.Articulo;
import com.proyecto.talento.carrito.model.Pedido;
import com.proyecto.talento.carrito.model.PedidoArticulo;
import com.proyecto.talento.carrito.repository.ArticuloRepository;
import com.proyecto.talento.carrito.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ArticuloRepository articuloRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ArticuloRepository articuloRepository) {
        this.pedidoRepository = pedidoRepository;
        this.articuloRepository = articuloRepository;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido guardarPedido(Pedido pedido) {
        if (pedido.getPedidoArticulos() != null) {
            for (PedidoArticulo pa : pedido.getPedidoArticulos()) {
                Long articuloId = pa.getArticulo().getId();
                Articulo articuloCompleto = articuloRepository.findById(articuloId).orElseThrow(() -> new RuntimeException("Artículo no encontrado: ID " + articuloId));
                pa.setArticulo(articuloCompleto);
                pa.setPedido(pedido);
            }
        }
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
