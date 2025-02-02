package com.platzi.pizzeria_presto.persistencia.projection;

import java.time.LocalDateTime;

public interface PedidoResumen {
    Integer getPedidoId();
    String getClienteNombre();
    LocalDateTime getPedidoFecha();
    Double getPedidoMontoTotal();
    String PizzaNombre();
}
