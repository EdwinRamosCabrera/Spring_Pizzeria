package com.platzi.pizzeria_presto.web.controller;

import com.platzi.pizzeria_presto.persistencia.entidad.Cliente;
import com.platzi.pizzeria_presto.servicio.ClienteService;
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
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "Controlador para los clientes")
public class ClienteController {

    @Autowired
    private final ClienteService clienteService;
    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @Operation(
            summary = "Búsqueda de cliente",
            description = "Muestra un cliente de la Pizzeria según su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientId(@PathVariable Long id){
        return clienteService.getClienteId(id)
                .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(
            summary = "Lista de clientes",
            description = "Muestra todos los clientes de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Clientes encontrados correctamente"),
                    @ApiResponse(responseCode = "404", description = "Clientes no encontrados")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> getAllCliente(){
        return clienteService.getAllCliente()
                .map(clientes -> new ResponseEntity<>(clientes, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(
            summary = "Creación de cliente",
            description = "Crea un nuevo cliente de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente creado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Cliente no creado")
            }
    )
    @PostMapping("/")
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        try{
            return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(
            summary = "Eliminación de cliente",
            description = "Elimina un cliente de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Cliente no eliminado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        try{
            return new ResponseEntity<>(clienteService.delete(id), HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
