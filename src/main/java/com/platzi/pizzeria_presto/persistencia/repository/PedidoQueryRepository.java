package com.platzi.pizzeria_presto.persistencia.repository;

import com.platzi.pizzeria_presto.persistencia.entidad.Pedido;
import com.platzi.pizzeria_presto.persistencia.projection.PedidoResumen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PedidoQueryRepository extends JpaRepository<Pedido, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM pedidos WHERE id_cliente = :id")
    List<Pedido> findPedidosCliente(@Param("id") String clienteId);

    @Query(nativeQuery = true, value =
            "SELECT pe.id_pedido AS pedidoId, pc.nombre AS clienteNombre, pe.fecha AS pedidoFecha, " +
            "       pe.monto_total AS pedidoMontoTotal, GROUP_CONCAT(pi.nombre) AS pizzaNombre " +
            "FROM pedidos pe " +
            "    INNER JOIN clientes pc ON pe.id_pedido = pc.id_cliente " +
            "    INNER JOIN detalle_pedido pdp ON pe.id_detalle_pedido = pdp.id_detalle_pedido " +
            "    INNER JOIN pizzas pi ON pdp.id_pizza = pi.id_pizza " +
            "WHERE pe.id_pedido = :pedidoId " +
            "GROUP BY pe.id_pedido, pc.nombre, pe.fecha, pe.monto_total ")
    PedidoResumen findSumaryPedido(@Param("pedidoId") long pedidoId);
}
