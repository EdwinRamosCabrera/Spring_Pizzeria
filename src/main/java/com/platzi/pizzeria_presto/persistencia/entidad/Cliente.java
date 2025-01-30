package com.platzi.pizzeria_presto.persistencia.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NonNull
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", length = 20)
    private Long clienteId;

    @Column(name = "documento_identidad", nullable = false, length = 8, unique = true)
    private String documentoIdentidad;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(length = 200)
    private String direccion;

    @Column(nullable = false, length = 100, unique = true)
    private String correo;

    @Column(nullable = false, length = 15)
    private String telefono;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidoList;
}
