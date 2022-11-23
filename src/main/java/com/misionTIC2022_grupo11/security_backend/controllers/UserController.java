package com.misionTIC2022_grupo11.security_backend.controllers;

import com.misionTIC2022_grupo11.security_backend.models.User;
import com.misionTIC2022_grupo11.security_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return this.userService.index();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id){
        return this.userService.show(id);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public User insertUser(@RequestBody User user){
        return this.userService.create(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HashMap<String, Boolean> loginUser(@RequestBody User user){
        return  this.userService.login(user);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@PathVariable("id") int id,@RequestBody User user){
        return this.userService.update(id, user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteUser(@PathVariable("id") int id){
        return this.userService.delete(id);
    }

}
