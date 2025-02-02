package com.platzi.pizzeria_presto.web.Controller;

import com.platzi.pizzeria_presto.persistencia.entidad.Cliente;
import com.platzi.pizzeria_presto.service.ClienteJPQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Cliente/")
public class ClienteJPQLController {

    private final ClienteJPQLService clienteJPQLService;
    @Autowired
    public ClienteJPQLController(ClienteJPQLService clienteJPQLService){
        this.clienteJPQLService = clienteJPQLService;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Cliente> getByPhone(@PathVariable String phone){
        return ResponseEntity.ok(this.clienteJPQLService.getByTelefono(phone));
    }
}
