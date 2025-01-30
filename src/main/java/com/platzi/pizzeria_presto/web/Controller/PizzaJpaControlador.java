package com.platzi.pizzeria_presto.web.Controller;

import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import com.platzi.pizzeria_presto.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pizza")
public class PizzaJpaControlador {

    private final PizzaService pizzaService;
    @Autowired
    public PizzaJpaControlador(PizzaService pizzaService){
        this.pizzaService = pizzaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pizza>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable int id){
        return this.pizzaService.getPizzaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Pizza> add(@RequestBody Pizza pizza){
        if(pizza.getPizzaId() == null || !this.pizzaService.exists(pizza.getPizzaId())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable int id, @RequestBody Pizza pizza){
        if(this.pizzaService.getPizzaById(id).isPresent()){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Pizza> actualizar(@RequestBody Pizza pizza){
        if(pizza.getPizzaId() != null && this.pizzaService.exists(pizza.getPizzaId())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Boolean> delete(@PathVariable int id){
        if(this.pizzaService.exists(id)){
            return ResponseEntity.ok(this.pizzaService.delete(id));
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping({"/eliminar/{id}"})
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        if(this.pizzaService.exists(id)){
            this.pizzaService.eliminar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
