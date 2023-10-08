package com.example.pizzaspringboot.controller;

import com.example.pizzaspringboot.Pizza;
import com.example.pizzaspringboot.service.PizzaService;
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
    public Pizza getPizzaWithId(@PathVariable int id) {
        return pizzaService.getPizzaWithId(id);
    }

    @PostMapping("/pizzas")
    public ResponseEntity<?> addPizza(@RequestBody Pizza pizza) {
        return pizzaService.addPizza(pizza);
    }

    @PutMapping("/pizzas/{id}")
    public ResponseEntity<?> updatePizza(@PathVariable int id, @RequestBody Pizza updatedPizza) {
        return pizzaService.updatePizza(id, updatedPizza);
    }

    @DeleteMapping("/pizzas/{id}")
    public ResponseEntity<String> deletePizza(@PathVariable int id) {
        return pizzaService.deletePizza(id);
    }
}
