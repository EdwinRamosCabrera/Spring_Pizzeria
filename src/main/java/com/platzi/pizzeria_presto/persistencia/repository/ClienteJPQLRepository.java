package com.platzi.pizzeria_presto.persistencia.repository;

import com.platzi.pizzeria_presto.persistencia.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteJPQLRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "SELECT c FROM Cliente c WHERE c.telefono = :telefono") // ":" significa que voy a tener un parámetro con el nombre de teléfono
    public Cliente findByTelefono(@Param("telefono") String telefono);


}
