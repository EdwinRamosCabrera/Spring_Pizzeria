package com.platzi.pizzeria_presto.servicio;

import com.platzi.pizzeria_presto.persistencia.entidad.Cliente;
import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import com.platzi.pizzeria_presto.persistencia.repositorio.ClienteRepository;
import com.platzi.pizzeria_presto.persistencia.repositorio.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    public Optional<Pizza> getPizzaId(int id){
        return pizzaRepository.findById(id);
    }

    public Optional<List<Pizza>> getAllPizza(){
        List<Pizza> pizzaList = (List<Pizza>) pizzaRepository.findAll();
        return Optional.of(pizzaList);
    }

    public Pizza save(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public boolean delete(int id){
        if(getPizzaId(id).isPresent()){
            pizzaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
