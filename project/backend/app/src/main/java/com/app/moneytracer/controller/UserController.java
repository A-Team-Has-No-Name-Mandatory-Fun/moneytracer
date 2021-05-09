package com.app.moneytracer.controller;

import com.app.moneytracer.configuration.PasswordConfig;
import com.app.moneytracer.controller.payload.SignupRequest;
import com.app.moneytracer.model.User;
import com.app.moneytracer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController 
{
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordConfig passwordConfig;

    public UserController(UserRepository userRepository, PasswordConfig passwordConfig)
    {
        this.userRepository = userRepository;
        this.passwordConfig = passwordConfig;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUser()
    {
        List<User> allUsers =  userRepository.findAll();

        return allUsers.size() > 0 ? new ResponseEntity<>(allUsers, HttpStatus.OK)
                : new ResponseEntity<>("No users available", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest request)
    {
        if (userRepository.existsByUsername(request.getUsername()))
        {
            return new ResponseEntity<>("Error: Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        try
        {
            User user = new User.Builder()
                    .withUsername(request.getUsername())
                    .withPassword(passwordConfig.passwordEncoder().encode(request.getPassword()))
                    .withFirstName(request.getFirstName())
                    .withLastName(request.getLastName())
                    .withEmail(request.getEmail())
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
