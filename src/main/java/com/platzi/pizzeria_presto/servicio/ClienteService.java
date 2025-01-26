package com.platzi.pizzeria_presto.servicio;

import com.platzi.pizzeria_presto.persistencia.entidad.Cliente;
import com.platzi.pizzeria_presto.persistencia.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> getClienteId(long id){
        return clienteRepository.findById(id);
    }

    public Optional<List<Cliente>> getAllCliente(){
        List<Cliente> clientesList = (List<Cliente>) clienteRepository.findAll();
        return Optional.of(clientesList);
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public boolean delete(long id){
        if(getClienteId(id).isPresent()){
            clienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
