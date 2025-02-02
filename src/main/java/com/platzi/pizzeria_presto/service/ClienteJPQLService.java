package com.platzi.pizzeria_presto.service;

import com.platzi.pizzeria_presto.persistencia.entidad.Cliente;
import com.platzi.pizzeria_presto.persistencia.repository.ClienteJPQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteJPQLService {

    private final ClienteJPQLRepository clienteJPQLRepository;
    @Autowired
    public ClienteJPQLService(ClienteJPQLRepository clienteJPQLRepository){
        this.clienteJPQLRepository = clienteJPQLRepository;
    }
    public Cliente getByTelefono(String telefono){
        return this.clienteJPQLRepository.findByTelefono(telefono);
    }
}
