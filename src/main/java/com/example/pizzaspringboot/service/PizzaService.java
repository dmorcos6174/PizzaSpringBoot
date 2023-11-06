package com.example.pizzaspringboot.service;

import com.example.pizzaspringboot.entity.Pizza;
import com.example.pizzaspringboot.exception.PizzaAlreadyExistsException;
import com.example.pizzaspringboot.exception.PizzaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final List<Pizza> pizzas = new ArrayList<>(Arrays.asList(
            new Pizza("0", "Chicken Ranch"),
            new Pizza("1", "Chicken BBQ")
    ));

    public List<Pizza> getAllPizzas() {
        return pizzas;
    }

    public Pizza getPizzaWithId(String id) throws PizzaNotFoundException {
        Optional<Pizza> pizza = pizzas.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (!pizza.isPresent()) {
            throw new PizzaNotFoundException("No pizza with the ID " + id + " exists.");
        }
        return pizza.get();
    }

    public void addPizza(Pizza pizza) throws PizzaAlreadyExistsException {
        if (pizzas.stream().anyMatch(p -> p.getId().equals(pizza.getId()) || p.getName().equals(pizza.getName()))) {
            throw new PizzaAlreadyExistsException("A pizza with the ID " + pizza.getId() + " or name " + pizza.getName() + " already exists.");
        }
        pizzas.add(pizza);
    }

    public void updatePizza(String id, Pizza updatedPizza) throws PizzaNotFoundException {
        int index = pizzas.indexOf(getPizzaWithId(id));
        if (index == -1) {
            throw new PizzaNotFoundException("No pizza with the ID " + id + " exists.");
        }
        pizzas.set(index, updatedPizza);
    }

    public void deletePizza(String id) throws PizzaNotFoundException {
        int index = pizzas.indexOf(getPizzaWithId(id));
        if (index == -1) {
            throw new PizzaNotFoundException("No pizza with the ID " + id + " exists.");
        }
        pizzas.remove(index);
    }

}
