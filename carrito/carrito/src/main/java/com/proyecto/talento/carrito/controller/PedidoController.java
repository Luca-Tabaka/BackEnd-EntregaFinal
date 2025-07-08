package com.proyecto.talento.carrito.controller;

import com.proyecto.talento.carrito.model.Pedido;
import com.proyecto.talento.carrito.model.PedidoArticulo;
import com.proyecto.talento.carrito.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        Optional<Pedido> pedidoOpt = pedidoService.obtenerPedidoPorId(id);
        return pedidoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        if (pedido.getPedidoArticulos() != null) {
            for (PedidoArticulo pa : pedido.getPedidoArticulos()) {
                pa.setPedido(pedido);
            }
        }
        return pedidoService.guardarPedido(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        if (pedidoService.obtenerPedidoPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoService.actualizarPedidoCompleto(id, pedido));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> actualizarParte(@PathVariable Long id, @RequestBody Pedido pedido) {
        if (pedidoService.obtenerPedidoPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoService.actualizarPartePedido(id, pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (pedidoService.obtenerPedidoPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
