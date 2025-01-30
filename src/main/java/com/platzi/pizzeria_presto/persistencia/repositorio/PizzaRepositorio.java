package com.platzi.pizzeria_presto.persistencia.repositorio;

import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepositorio extends CrudRepository<Pizza, Integer> {

    public List<Pizza> findAllByEstadoOrderByPrecio(String estado);

    public List<Pizza> findAllByNombreIgnoreCaseOrderByPizzaId(String nombre);

    public List<Pizza> findAllByDescripcionContainingIgnoreCase(String descripcion);

    public Optional<Pizza> findFirstByEstadoAndNombreIgnoreCase(String estado, String nombre);

    public List<Pizza> findTop3ByEstadoAndPrecioLessThanEqualOrderByPrecio(double precio);

}
