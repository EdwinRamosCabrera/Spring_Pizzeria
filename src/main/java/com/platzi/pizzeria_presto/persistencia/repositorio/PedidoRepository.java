package com.platzi.pizzeria_presto.persistencia.repositorio;

import com.platzi.pizzeria_presto.persistencia.entidad.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    public List<Pedido> findAllByFechaAfter(LocalDateTime date);

    public List<Pedido> findAllByMetodoPagoIn(List<String> methods);
}
