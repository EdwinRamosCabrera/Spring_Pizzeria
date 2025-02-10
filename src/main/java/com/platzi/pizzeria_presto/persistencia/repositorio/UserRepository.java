package com.platzi.pizzeria_presto.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.platzi.pizzeria_presto.persistencia.entidad.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String name);
}
