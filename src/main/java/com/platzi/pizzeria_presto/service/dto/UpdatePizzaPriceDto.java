package com.platzi.pizzeria_presto.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {

    private Integer idPizza;

    private Double newPrice;
}
