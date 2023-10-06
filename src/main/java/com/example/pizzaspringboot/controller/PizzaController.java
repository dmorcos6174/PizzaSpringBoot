package com.example.pizzaspringboot.controller;

import com.example.pizzaspringboot.Pizza;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PizzaController {

    List<Pizza> pizzas = new ArrayList<>(Arrays.asList(
            new Pizza(0, "Chicken Ranch"),
            new Pizza(1, "Chicken BBQ")
    ));

    @GetMapping("/pizza/getAllPizzas")
    public List<Pizza> getAllPizzas() {
        return pizzas;
    }

    @GetMapping("/pizza/getPizzaWithId/{id}")
    public Pizza getPizzaWithId(@PathVariable int id) {
        return pizzas.get(id);
    }

    @PostMapping("/pizza/add")
    public ResponseEntity<?> addPizza(@RequestBody Pizza pizza) {
        if (!pizzas.contains(pizza)) {
            pizzas.add(pizza);
            return ResponseEntity.ok(pizza);
        }
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).body(pizza.getDressing() + " already exists.");
    }

    @PutMapping("/pizza/update/{id}")
    public ResponseEntity<?> updatePizza(@PathVariable int id, @RequestBody Pizza updatedPizza) {
        try {
            pizzas.set(id, updatedPizza);
            return ResponseEntity.ok(pizzas.get(id-1));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pizza with such ID: " + id);
        }
    }

    @DeleteMapping("/pizza/delete/{id}")
    public ResponseEntity<String> deletePizza(@PathVariable int id) {
        try {
            pizzas.remove(id);
            return ResponseEntity.ok("Pizza deleted successfully");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pizza with such ID" + id);
        }
    }
}
