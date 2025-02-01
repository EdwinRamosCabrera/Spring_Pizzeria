package com.platzi.pizzeria_presto.persistencia.repository;

import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<Pizza, Integer> {

    Page<Pizza> findByEstado(String estado, Pageable pageable); // este findBy funciona igual que el FindAll
}
