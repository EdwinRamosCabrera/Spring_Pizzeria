package com.platzi.pizzeria_presto.persistencia.repositorio;

import com.platzi.pizzeria_presto.persistencia.entidad.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
