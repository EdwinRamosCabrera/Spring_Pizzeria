package com.platzi.pizzeria_presto.persistencia.repository;

import com.platzi.pizzeria_presto.persistencia.entidad.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoQueryRepository extends JpaRepository<Pedido, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM pedidos WHERE id_cliente = :id")
    public List<Pedido> findPedidosCliente(@Param("id") String clienteId);
}
