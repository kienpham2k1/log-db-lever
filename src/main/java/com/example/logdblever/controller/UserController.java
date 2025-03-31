package com.example.logdblever.controller;

import com.example.logdblever.model.User;
import com.example.logdblever.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping
    public User getUserById(@RequestParam int id) {
        return userService.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping
    public void deleteUserById(@RequestParam int id) {
        userService.delete(id);
    }
}
