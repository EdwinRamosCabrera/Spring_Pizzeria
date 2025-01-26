package com.platzi.pizzeria_presto.servicio;

import com.platzi.pizzeria_presto.persistencia.entidad.DetallePedido;
import com.platzi.pizzeria_presto.persistencia.repositorio.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    @Autowired
    private final DetallePedidoRepository detallePedidoRepository;
    @Autowired
    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository){
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public Optional<DetallePedido> getDetallePedidoId(long id){
        return detallePedidoRepository.findById(id);
    }

    public Optional<List<DetallePedido>> getAllDetallePedido(){
        List<DetallePedido> detallePedidoList = (List<DetallePedido>) detallePedidoRepository.findAll();
        return Optional.of(detallePedidoList);
    }

    public DetallePedido save(DetallePedido detallePedido){
        return detallePedidoRepository.save(detallePedido);
    }

    public boolean delete(long id){
        try{
            detallePedidoRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
