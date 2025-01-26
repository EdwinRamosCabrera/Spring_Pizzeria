package com.platzi.pizzeria_presto.web.controller;

import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import com.platzi.pizzeria_presto.servicio.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pizzas")
@Tag(name = "Pizza", description = "Controlador para las pizzas")
public class PizzaController {

    @Autowired
    private final PizzaService pizzaService;
    @Autowired
    public PizzaController(PizzaService pizzaService){
        this.pizzaService = pizzaService;
    }

    @Operation(
            summary = "Búsqueda de pizza",
            description = "Muestra una pizza de la Pizzeria según su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pizza encontrada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pizza no encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaId(@PathVariable int id){
        return pizzaService.getPizzaId(id)
                .map(pizza -> new ResponseEntity<>(pizza, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(
            summary = "Lista de pizzas",
            description = "Muestra todos las pizzas de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pizzas encontradas correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pizzas no encontradas")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Pizza>> getAllPizza(){
        return pizzaService.getAllPizza()
                .map(pizza -> new ResponseEntity<>(pizza, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(
            summary = "Creación de pizza",
            description = "Crea un nueva pizza de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pizza creada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pizza no creada")
            }
    )
    @PostMapping("/")
    public ResponseEntity<Pizza> save(@RequestBody Pizza pizza){
        try{
            return new ResponseEntity<>(pizzaService.save(pizza), HttpStatus.CREATED);
        } catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(
            summary = "Eliminación de pizza",
            description = "Elimina una pizza de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pizza eliminada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pizza no eliminada")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id){
        if(pizzaService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
