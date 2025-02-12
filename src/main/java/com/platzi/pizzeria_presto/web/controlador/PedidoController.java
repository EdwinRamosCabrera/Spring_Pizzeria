package com.platzi.pizzeria_presto.web.controlador;

import com.platzi.pizzeria_presto.persistencia.entidad.Pedido;
import com.platzi.pizzeria_presto.servicio.PedidoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/pedidos")
@Tag(name = "Pedido", description = "Controlador para los pedidos")
public class PedidoController {

    @Autowired
    private final PedidoServicio pedidoServicio;
    @Autowired
    public PedidoController(PedidoServicio pedidoServicio){
        this.pedidoServicio = pedidoServicio;
    }
    @Operation(
            summary = "Búsqueda de pedido",
            description = "Muestra un pedido de la Pizzeria según su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido encontrado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoId(@PathVariable long id){
        return pedidoServicio.getPedidoId(id)
                .map(pedido -> new ResponseEntity<>(pedido, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(
            summary = "Lista de pedidos",
            description = "Muestra todos los pedidos de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedidos encontrados correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pedidos no encontrados")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Pedido>> getAllPedido(){
        return pedidoServicio.getAllPedido()
                .map(pedido -> new ResponseEntity<>(pedido, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(
            summary = "Creación de pedido",
            description = "Crea un nuevo pedido de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido creado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pedido no creado")
            }
    )
    @PostMapping("/")
    public ResponseEntity<Pedido> save(@RequestBody Pedido pedido){
        try{
            return new ResponseEntity<>(pedidoServicio.save(pedido), HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(
            summary = "Eliminación de pedido",
            description = "Elimina un pedido de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pedido no eliminado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        if(pedidoServicio.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Pedidos de hoy",
            description = "Muestra los pedidos del dia de hoy de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedidos encontrados correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pedido no encontrados")
            }
    )
    @GetMapping("/today")
    public ResponseEntity<List<Pedido>> getTodayOrders(){
        try{
            return ResponseEntity.ok(this.pedidoServicio.getTodayOrders());
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(
            summary = "Pedidos externos",
            description = "Muestra los pedidos que no se consumieron dentro de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedidos encontrados correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pedido no encontrados")
            }
    )
    @GetMapping("/outside")
    public ResponseEntity<List<Pedido>> getOutsideOrders(){
        try{
            return ResponseEntity.ok(this.pedidoServicio.getOutsideOrders());
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
