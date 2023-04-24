package com.democms.cms.controller;

import com.democms.cms.entity.User;
import com.democms.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/create")
    User addUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users/all")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{username}")
    ResponseEntity<Map<?,?>> getOneUser(@PathVariable String username){
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            Map<String,String> response = new HashMap<>();
            response.put("message", "No user found with that username.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/v2/user")
    ResponseEntity<Map<?,?>> getUserByID(@RequestParam("id") Long user_id){
        Optional<User> user = userRepository.findById(user_id);
        if(user.isPresent()){
            Map<String,Object> response = new HashMap<>();
            response.put("user", user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            Map<String,String> response = new HashMap<>();
            response.put("message", "No user found with that id.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
