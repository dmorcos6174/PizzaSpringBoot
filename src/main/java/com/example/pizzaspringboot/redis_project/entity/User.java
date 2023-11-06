package com.example.pizzaspringboot.redis_project.entity;

import com.example.pizzaspringboot.center_mgmt.enums.Gender;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("User")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User implements Serializable {
    private String id;
    private String name;
    private Gender gender;
}
