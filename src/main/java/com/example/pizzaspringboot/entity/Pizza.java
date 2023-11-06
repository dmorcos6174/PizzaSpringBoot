package com.example.pizzaspringboot.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Pizza {
    @NotNull(message = "id field cannot be null")
    String id;
    @Size(min = 2, max = 30, message = "name field has to be 2-30 characters")
    String name;
}
