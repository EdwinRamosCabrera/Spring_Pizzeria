package com.platzi.pizzeria_presto.servicio;

import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import com.platzi.pizzeria_presto.persistencia.repositorio.PizzaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaServicio {

    @Autowired
    private final PizzaRepositorio pizzaRepositorio;

    @Autowired
    public PizzaServicio(PizzaRepositorio pizzaRepositorio){
        this.pizzaRepositorio = pizzaRepositorio;
    }

    public Optional<Pizza> getPizzaId(int id){
        return this.pizzaRepositorio.findById(id);
    }

    public Optional<List<Pizza>> getAllPizza(){
        List<Pizza> pizzaList = (List<Pizza>) this.pizzaRepositorio.findAll();
        return Optional.of(pizzaList);
    }

    public Pizza save(Pizza pizza){
        return this.pizzaRepositorio.save(pizza);
    }

    public boolean delete(int id){
        if(getPizzaId(id).isPresent()){
            this.pizzaRepositorio.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<List<Pizza>> getAllByAvailable(String estado){
        return Optional.of(this.pizzaRepositorio.findAllByEstadoOrderByPrecio(estado));
    }

    public List<Pizza> getAllByName(String nombre){
        return this.pizzaRepositorio.findAllByNombreIgnoreCaseOrderByPizzaId(nombre);
    }

    public List<Pizza> getAllByDescription(String description){
        return this.pizzaRepositorio.findAllByDescripcionContainingIgnoreCase(description);
    }

    public Pizza getFirstByEstadoAndName(String estado, String nombre){
        return this.pizzaRepositorio.findFirstByEstadoAndNombreIgnoreCase(estado, nombre)
                .orElseThrow(()-> new RuntimeException("La pizza no existe"));
    }

    public List<Pizza> get3Cheapest(String estado, double precio){
        return this.pizzaRepositorio.findTop3ByEstadoAndPrecioLessThanEqualOrderByPrecio(estado, precio);
    }
}
