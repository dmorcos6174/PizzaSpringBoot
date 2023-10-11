package com.example.pizzaspringboot.controller;

import com.example.pizzaspringboot.Pizza;
import com.example.pizzaspringboot.exception.PizzaAlreadyExistsException;
import com.example.pizzaspringboot.exception.PizzaNotFoundException;
import com.example.pizzaspringboot.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/pizzas")
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/pizzas/{id}")
    public Pizza getPizzaWithId(@PathVariable String id) throws PizzaNotFoundException {
        return pizzaService.getPizzaWithId(id);
    }

    @PostMapping("/pizzas")
    public Pizza addPizza(@RequestBody @Valid Pizza pizza) throws PizzaAlreadyExistsException {
        pizzaService.addPizza(pizza);
        return pizza;
    }

    @PutMapping("/pizzas/{id}")
    public Pizza updatePizza(@PathVariable String id, @RequestBody @Valid Pizza updatedPizza) throws PizzaNotFoundException {
        pizzaService.updatePizza(id, updatedPizza);
        return updatedPizza;
    }

    @DeleteMapping("/pizzas/{id}")
    public void deletePizza(@PathVariable String id) throws PizzaNotFoundException {
        pizzaService.deletePizza(id);
    }
}
