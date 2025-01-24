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
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", length = 20)
    private Long clienteId;

    @Column(name = "documento_identidad", length = 8)
    private String documentoIdentidad;

    private String nombre;

    private String apellidos;

    private String direccion;

    private String correo;

    private String telefono;
}
