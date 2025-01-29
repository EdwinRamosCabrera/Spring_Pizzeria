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
        return pizzaRepositorio.findById(id);
    }

    public Optional<List<Pizza>> getAllPizza(){
        List<Pizza> pizzaList = (List<Pizza>) pizzaRepositorio.findAll();
        return Optional.of(pizzaList);
    }

    public Pizza save(Pizza pizza){
        return pizzaRepositorio.save(pizza);
    }

    public boolean delete(int id){
        if(getPizzaId(id).isPresent()){
            pizzaRepositorio.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
