package com.platzi.pizzeria_presto.servicio;

import com.platzi.pizzeria_presto.persistencia.entidad.Pedido;
import com.platzi.pizzeria_presto.persistencia.repositorio.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServicio {

    @Autowired
    private final PedidoRepository pedidoRepository;

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SIDE = "S";

    @Autowired
    public PedidoServicio(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public Optional<Pedido> getPedidoId(long id){
        return pedidoRepository.findById(id);
    }

    public Optional<List<Pedido>> getAllPedido(){
        List<Pedido> pedidoList = (List<Pedido>) pedidoRepository.findAll();
        return Optional.of(pedidoList);
    }

    public Pedido save(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public boolean delete(long id){
        try{
            pedidoRepository.deleteById(id);
            return true;
        }catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public List<Pedido> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.pedidoRepository.findAllByFechaAfter(today);
    }

    public List<Pedido> getOutsideOrders(){
        List<String> methods = Arrays.asList("DELIVERY", "CARRYOUT");
        return this.pedidoRepository.findAllByMetodoPagoIn(methods);
    }
}
