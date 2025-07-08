package com.proyecto.talento.carrito.repository;
import com.proyecto.talento.carrito.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    /* Estado */
    List<Pedido> findByEstado(String estado); 
    List<Pedido> findByEstadoIgnoreCaseContaining(String estado);
    /* Fecha */
    List<Pedido> findByFechaPedido(String fechaPedido);
    List<Pedido> findByFechaEntrega(String fechaEntrega);
    List<Pedido> findByFechaPedidoBetween(String fechaInicio, String fechaFin);
    List<Pedido> findByEstadoAndFechaPedido(String estado, String fechaPedido);
}
