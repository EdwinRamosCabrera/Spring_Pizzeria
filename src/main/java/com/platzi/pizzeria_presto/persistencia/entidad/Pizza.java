package com.platzi.pizzeria_presto.persistencia.entidad;


import com.platzi.pizzeria_presto.persistencia.audit.AuditPizzaListener;
import com.platzi.pizzeria_presto.persistencia.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Getter
@Setter
@NonNull
@NoArgsConstructor
@Entity
@Table(name = "pizzas")
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
public class Pizza extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza")
    private Integer pizzaId;

    @Column(nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(nullable = false, length = 300)
    private String descripcion;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double precio;

    @Column(nullable = false)
    private String estado;

    @Override
    public String toString() {
        return "Pizza{" +
                "pizzaId=" + pizzaId +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", estado='" + estado + '\'' +
                '}';
    }

   /* No es necesario crearla
    @OneToMany(mappedBy = "pizza")
    private List<DetallePedido> detallePedidoList;
     */
}
