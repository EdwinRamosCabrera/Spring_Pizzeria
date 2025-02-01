package com.platzi.pizzeria_presto.service;

import com.platzi.pizzeria_presto.persistencia.entidad.Pizza;
import com.platzi.pizzeria_presto.persistencia.repository.PizzaPagSortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PizzaPagSortService {

    @Autowired
    private final PizzaPagSortRepository pizzaPagSortRepository;
    @Autowired
    public PizzaPagSortService(PizzaPagSortRepository pizzaPagSortRepository){
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<Pizza> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public Page<Pizza> getAllAvailable(String estado, int page, int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByEstado(estado, pageRequest);
    }
}
