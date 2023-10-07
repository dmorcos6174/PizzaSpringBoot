package com.example.pizzaspringboot.service;

import com.example.pizzaspringboot.Pizza;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PizzaService {

    private List<Pizza> pizzas = new ArrayList<>(Arrays.asList(
            new Pizza(0, "Chicken Ranch"),
            new Pizza(1, "Chicken BBQ")
    ));

    public List<Pizza> getAllPizzas() {
        return pizzas;
    }

    public Pizza getPizzaWithId(int id) {
        return pizzas.get(id);
    }

    public ResponseEntity<?> addPizza(Pizza pizza) {
        if (!pizzas.contains(pizza)) {
            pizzas.add(pizza);
            return ResponseEntity.ok(pizza);
        }
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).body(pizza.getDressing() + " already exists.");
    }

    public ResponseEntity<?> updatePizza(int id, Pizza updatedPizza) {
        try {
            pizzas.set(id, updatedPizza);
            return ResponseEntity.ok(pizzas.get(id-1));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pizza with such ID: " + id);
        }
    }

    public ResponseEntity<String> deletePizza(int id) {
        try {
            pizzas.remove(id);
            return ResponseEntity.ok("Pizza deleted successfully");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pizza with such ID" + id);
        }
    }

}
