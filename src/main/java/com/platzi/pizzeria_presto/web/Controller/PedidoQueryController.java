package com.platzi.pizzeria_presto.web.Controller;

import com.platzi.pizzeria_presto.persistencia.entidad.Pedido;
import com.platzi.pizzeria_presto.service.PedidoQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoQueryController {

    private final PedidoQueryService pedidoQueryService;

    public PedidoQueryController(PedidoQueryService pedidoQueryService){
        this.pedidoQueryService = pedidoQueryService;
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Pedido>> getByCliente(@PathVariable String idCliente){
        return ResponseEntity.ok(this.pedidoQueryService.getByCliente(idCliente));
    }

    @GetMapping("sumary/{pedidoId}")
    public ResponseEntity<Pedido> getSumaryPedido(@PathVariable long pedidoId){
        Pedido pedido = this.pedidoQueryService.getSumaryPedido(pedidoId);
        try{
            return ResponseEntity.ok(pedido);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
