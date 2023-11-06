package com.example.pizzaspringboot.redis_project.service;


import com.example.pizzaspringboot.redis_project.entity.User;
import com.example.pizzaspringboot.redis_project.repo.UserRepo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Cacheable(cacheNames = "cache1", key = "'#key'")
    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUserById(String id) {
        userRepo.deleteById(id);
    }
}
