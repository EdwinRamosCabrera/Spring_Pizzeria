package com.platzi.pizzeria_presto.persistencia.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NonNull
@NoArgsConstructor
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pedido")
    private Long detallePedidoId;

    @Column(name = "id_pedido")
    private Long pedidoId;

    @Column(name = "id_pizza")
    private Integer pizzaId;

    private Integer cantidad;

    private Double precio;

    private String comentarios;
}
