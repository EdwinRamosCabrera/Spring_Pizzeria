package com.platzi.pizzeria_presto.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/hello")
    public String welcome(){
        return "Bienvenidos a la web de Pizzeria Presto";
    }
}
