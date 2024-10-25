package com.outhelix.controller;

import com.outhelix.model.User;
import com.outhelix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

//    @PostMapping("/create")
//    public void createUser (@RequestBody User user) {
//        userService.saveUser(user);
//    }
}
