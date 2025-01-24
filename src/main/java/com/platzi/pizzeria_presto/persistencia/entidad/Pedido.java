package com.platzi.pizzeria_presto.persistencia.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NonNull
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer pedidoId;

    @Column(name = "id_cliente")
    private Integer clienteId;

    @Column(name = "id_detalle_pedido")
    private Integer detallePedidoId;

    @Column(name = "monto_total")
    private Double montoTotal;

    @Column(name = "metodo_pago")
    private String metodoPago;

    private LocalDate fecha;

    private String estado;

    private String comentarios;
}
