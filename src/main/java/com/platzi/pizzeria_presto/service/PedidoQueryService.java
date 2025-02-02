package com.platzi.pizzeria_presto.service;

import com.platzi.pizzeria_presto.persistencia.entidad.Pedido;
import com.platzi.pizzeria_presto.persistencia.repository.PedidoQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoQueryService {

    private final PedidoQueryRepository pedidoQueryRepository;
    @Autowired
    public PedidoQueryService(PedidoQueryRepository pedidoQueryRepository){
        this.pedidoQueryRepository = pedidoQueryRepository;
    }

    public List<Pedido> getByCliente(String idCliente){
        return this.pedidoQueryRepository.findPedidosCliente(idCliente);
    }

    public Pedido getSumaryPedido(long pedidoId){
        return this.getSumaryPedido(pedidoId);
    }
}
