package com.example.pizzaspringboot.controller;

import com.example.pizzaspringboot.entity.Pizza;
import com.example.pizzaspringboot.exception.PizzaAlreadyExistsException;
import com.example.pizzaspringboot.exception.PizzaNotFoundException;
import com.example.pizzaspringboot.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PizzaController {

    private final PizzaService pizzaService;

    private final RestTemplate restTemplate;

    private final WebClient.Builder webClientBuilder;

    public PizzaController(PizzaService pizzaService, RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.pizzaService = pizzaService;
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
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

    ////////////////////////////////////////////////////////////

    @GetMapping("/sayHello")
    public Mono<String> getSayHello() {
//        return restTemplate.getForObject("http://helloworld/helloworld/hello/sayHello", String.class);
        return webClientBuilder.build()
                .get()
                .uri("http://helloworld/helloworld/hello/sayHello")
                .retrieve()
                .bodyToMono(String.class);
    }
}
