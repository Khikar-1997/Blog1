package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public void create(@RequestBody User user){
        userService.create(user);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<ArrayList<User>> selectAllUsers(){
        ArrayList<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id){
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/user/{id}")
    public void update(@PathVariable int id,@RequestBody User user){
        userService.update(id,user);
    }

    @DeleteMapping(value = "/user/{id}")
    public void delete(@PathVariable int id){
        userService.delete(id);
    }

    @GetMapping(value = "/user/{username}/{password}")
    public ResponseEntity<Boolean> login(@RequestBody String username,@RequestBody String password){
        boolean login = userService.login(username,password);
        return ResponseEntity.ok(login);
    }
}
