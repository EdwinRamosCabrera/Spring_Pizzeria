package com.platzi.pizzeria_presto.web.controlador;

import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import com.platzi.pizzeria_presto.servicio.PizzaServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
@Tag(name = "Pizza", description = "Controlador para las pizzas")
public class PizzaController {

    @Autowired
    private final PizzaServicio pizzaServicio;
    @Autowired
    public PizzaController(PizzaServicio pizzaServicio){
        this.pizzaServicio = pizzaServicio;
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
        return pizzaServicio.getPizzaId(id)
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
        return pizzaServicio.getAllPizza()
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
            return new ResponseEntity<>(pizzaServicio.save(pizza), HttpStatus.CREATED);
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
        if(pizzaServicio.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Lista de pizzas disponibles",
            description = "Muestra la lista de pizzas que están disponibles de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pizzas encontradas correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pizzas no encontradas")
            }
    )
    @GetMapping("/available/{estado}")
    public ResponseEntity<List<Pizza>> getPizzaAvailable(@PathVariable String estado){
        return this.pizzaServicio.getAllByAvailable(estado)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Lista de pizzas por nombre",
            description = "Muestra la lista de pizzas por su nombre en la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pizzas encontradas correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pizzas no encontradas")
            }
    )
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Pizza>> getPizzaName(@PathVariable String name){
        try{
            return ResponseEntity.ok(pizzaServicio.getAllByName(name));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }
    @Operation(
            summary = "Lista de pizzas por descripción",
            description = "Muestra la lista de pizzas por su descripción en la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pizzas encontradas correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pizzas no encontradas")
            }
    )
    @GetMapping("/description/{ingrediente}")
    public ResponseEntity<List<Pizza>> getPizzaDescription(@PathVariable String ingrediente){
        try{
            return ResponseEntity.ok(this.pizzaServicio.getAllByDescription(ingrediente));
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
