package com.example.project.conrtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.project.model.User;
import com.example.project.model.UserDTO;
import com.example.project.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.getOne(id));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/user")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user){

        return  ResponseEntity.ok(userService.addUser(user));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable("id") Integer id){

        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user, @PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.updateUser(user));
    }

}

