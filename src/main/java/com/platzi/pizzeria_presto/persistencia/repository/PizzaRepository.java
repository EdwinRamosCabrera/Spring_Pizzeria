package com.platzi.pizzeria_presto.persistencia.repository;

import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {
}
