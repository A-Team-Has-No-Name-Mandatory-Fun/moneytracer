package com.app.moneytracer.controller;

import com.app.moneytracer.model.User;
import com.app.moneytracer.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController 
{
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUser()
    {
        List<User> allUsers =  userRepository.findAll();

        return allUsers.size() > 0 ? new ResponseEntity<>(allUsers, HttpStatus.OK)
                : new ResponseEntity<>("No users available", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody String name)
    {
        try
        {
            User user = new User.Builder()
                    .withName(name)
                    .build();
            userRepository.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
