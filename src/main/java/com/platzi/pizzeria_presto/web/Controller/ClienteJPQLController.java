package com.platzi.pizzeria_presto.web.Controller;

import com.platzi.pizzeria_presto.persistencia.entidad.Cliente;
import com.platzi.pizzeria_presto.service.ClienteJPQLService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Cliente/")
public class ClienteJPQLController {

    private final ClienteJPQLService clienteJPQLService;
    @Autowired
    public ClienteJPQLController(ClienteJPQLService clienteJPQLService){
        this.clienteJPQLService = clienteJPQLService;
    }

    @Operation(
            summary = "Cliente según su teléfono",
            description = "Busca el cliente según el numero de teléfono",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
            }
    )
    @GetMapping("/phone/{phone}")
    public ResponseEntity<Cliente> getByPhone(@PathVariable String phone){
        return ResponseEntity.ok(this.clienteJPQLService.getByTelefono(phone));
    }
}
