package com.platzi.pizzeria_presto.persistencia.repositorio;

import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Integer> {
}
