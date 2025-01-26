package com.platzi.pizzeria_presto.persistencia.repositorio;

import com.platzi.pizzeria_presto.persistencia.entidad.DetallePedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends CrudRepository<DetallePedido, Long> {
}
