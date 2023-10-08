package com.example.pizzaspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaSpringBootApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(PizzaSpringBootApplication.class, args);
        context.close();
    }

}
