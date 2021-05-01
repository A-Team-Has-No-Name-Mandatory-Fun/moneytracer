package com.app.moneytracer.repository;

import com.app.moneytracer.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long>
{
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
