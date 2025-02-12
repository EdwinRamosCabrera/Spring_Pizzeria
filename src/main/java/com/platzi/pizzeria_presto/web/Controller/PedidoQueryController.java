package com.platzi.pizzeria_presto.web.Controller;

import com.platzi.pizzeria_presto.persistencia.entidad.Pedido;
import com.platzi.pizzeria_presto.persistencia.projection.PedidoResumen;
import com.platzi.pizzeria_presto.service.PedidoQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/pedidos")
public class PedidoQueryController {

    private final PedidoQueryService pedidoQueryService;

    public PedidoQueryController(PedidoQueryService pedidoQueryService){
        this.pedidoQueryService = pedidoQueryService;
    }

    @Operation(
            summary = "Pedido según el cliente",
            description = "Muestra el pedido según el cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido encontrado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
            }
    )
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Pedido>> getByCliente(@PathVariable String idCliente){
        List<Pedido> pedidos = this.pedidoQueryService.getByCliente(idCliente);
        try{
            if(!pedidos.isEmpty()){
                return ResponseEntity.ok(pedidos);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(
            summary = "Resumen de pedido",
            description = "Muestra el pedido con campos de sus otras tablas relacionadas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido encontrado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
            }
    )
    @GetMapping("sumary/{pedidoId}")
    public ResponseEntity<PedidoResumen> getSumaryPedido(@PathVariable long pedidoId){
        PedidoResumen pedido = this.pedidoQueryService.getSumaryPedido(pedidoId);
        try{
            return ResponseEntity.ok(pedido);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
