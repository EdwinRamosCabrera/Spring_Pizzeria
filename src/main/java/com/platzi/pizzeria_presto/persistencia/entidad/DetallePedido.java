package com.platzi.pizzeria_presto.persistencia.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "id_pedido", nullable = false)
    private Long pedidoId;

    @Column(name = "id_pizza", nullable = false)
    private Integer pizzaId;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double precio;

    @Column(length = 200)
    private String comentarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido",referencedColumnName = "id_pedido", insertable = false, updatable = false)
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza", insertable = false, updatable = false)
    private Pizza pizza;
}
