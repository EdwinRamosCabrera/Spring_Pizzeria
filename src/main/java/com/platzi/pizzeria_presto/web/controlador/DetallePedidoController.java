package com.platzi.pizzeria_presto.web.controlador;

import com.platzi.pizzeria_presto.persistencia.entidad.DetallePedido;
import com.platzi.pizzeria_presto.servicio.DetallePedidoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/detalles_pedidos")
@Tag(name = "Detalle de Pedido", description = "Controlador para el detalle de los pedidos")
public class DetallePedidoController {

    @Autowired
    private final DetallePedidoServicio detallePedidoServicio;
    @Autowired
    public DetallePedidoController(DetallePedidoServicio detallePedidoServicio){
        this.detallePedidoServicio = detallePedidoServicio;
    }

    @Operation(
            summary = "Búsqueda de detalle de pedido",
            description = "Muestra un detalle de pedido de la Pizzeria según su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Detalle de pedido encontrado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Detalle de pedido no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> getDetallePedidoId(@PathVariable long id){
        return detallePedidoServicio.getDetallePedidoId(id)
                .map(detalle -> new ResponseEntity<>(detalle, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(
            summary = "Lista de detalle de pedidos",
            description = "Muestra todos los detalles de pedidos de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Detalles de pedidos encontrados correctamente"),
                    @ApiResponse(responseCode = "404", description = "Detalles de pedidos no encontrados")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<DetallePedido>> getAllDetallePedido(){
        return detallePedidoServicio.getAllDetallePedido()
                .map(detalle -> new ResponseEntity<>(detalle, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(
            summary = "Creación de detalle de pedido",
            description = "Crea un nuevo detalle de pedido de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Detalle creado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Detalle no creado")
            }
    )
    @PostMapping("/")
    public ResponseEntity<DetallePedido> save(@RequestBody DetallePedido detallePedido){
        try {
            return new ResponseEntity<>(detallePedidoServicio.save(detallePedido), HttpStatus.CREATED);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(
            summary = "Eliminación de detalle de pedido",
            description = "Elimina un detalle de pedido de la Pizzeria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Detalle de pedido eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Detalle de pedido no eliminado")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        if(detallePedidoServicio.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
