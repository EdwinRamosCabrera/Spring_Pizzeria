package com.platzi.pizzeria_presto.persistencia.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private Long pedidoId;

    @Column(name = "id_cliente", nullable = false)
    private Long clienteId;

    @Column(name = "id_detalle_pedido", nullable = false)
    private Integer detallePedidoId;

    @Column(name = "monto_total", nullable = false, columnDefinition = "Decimal(6,2)")
    private Double montoTotal;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false, length = 10)
    private String estado;

    @Column(length = 200)
    private String comentarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    @OrderBy("precio ASC")
    private List<DetallePedido> detallePedidoList;
}
