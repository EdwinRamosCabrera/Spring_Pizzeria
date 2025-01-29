package com.platzi.pizzeria_presto.web.controlador;

import com.platzi.pizzeria_presto.persistencia.entidad.Cliente;
import com.platzi.pizzeria_presto.servicio.ClienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "Controlador para los clientes")
public class ClienteController {

    @Autowired
    private final ClienteServicio clienteServicio;
    @Autowired
    public ClienteController(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
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
        return clienteServicio.getClienteId(id)
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
        return clienteServicio.getAllCliente()
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
            return new ResponseEntity<>(clienteServicio.save(cliente), HttpStatus.CREATED);
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
            return new ResponseEntity<>(clienteServicio.delete(id), HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
