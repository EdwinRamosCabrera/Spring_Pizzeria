package com.platzi.pizzeria_presto.persistencia.repository;

import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import com.platzi.pizzeria_presto.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {
    /*
    @Query(value = "UPDATE pizzas " +
            "SET precio = :newPrice " +
            "WHERE id_pizza = :idPizza ", nativeQuery = true)
    void updatePrice(@Param("idPizza") int idPizza, @Param("newQuery") double newPrice);
} */

@Query(value = "UPDATE pizzas " +
        "SET precio = :#{#newPizzaPrice.newPrice} " +
        "WHERE id_pizza = :#{#newPizzaPrice.idPizza} ", nativeQuery = true)
@Modifying
void updateNewPrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);
}



