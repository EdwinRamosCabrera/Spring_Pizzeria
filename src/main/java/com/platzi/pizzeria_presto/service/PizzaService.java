package com.platzi.pizzeria_presto.service;

import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import com.platzi.pizzeria_presto.persistencia.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAll(){
        return this.pizzaRepository.findAll();
    }

    public Optional<Pizza> getPizzaById(int id){
        return this.pizzaRepository.findById(id);
    }

    public Pizza save(Pizza pizza){
        return this.pizzaRepository.save(pizza);
    }

    public boolean delete(int id){
        if(getPizzaById(id).isPresent()){
           pizzaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void eliminar (int id){
        this.pizzaRepository.deleteById(id);
    }

    public boolean exists(int id){
        return this.pizzaRepository.existsById(id);
    }
}
