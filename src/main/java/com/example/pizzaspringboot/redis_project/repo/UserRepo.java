package com.example.pizzaspringboot.redis_project.repo;

import com.example.pizzaspringboot.redis_project.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, String> {
}
