package com.example.pizzaspringboot;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Pizza {
    @NotNull(message = "id field cannot be null")
    String id;
    @Size(min = 2, max = 30, message = "name field has to be 2-30 characters")
    String name;

    public Pizza(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
