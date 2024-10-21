package com.sec.controller;

import com.sec.models.UserDetails;
import com.sec.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //create
    @PostMapping("/")
    public UserDetails addUser(@RequestBody UserDetails userDetails) {
        return userService.AddingUser(userDetails);
    }

    //get all
    @GetMapping("/")
    public List<UserDetails> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public UserDetails getSingleUser(@PathVariable String username) {
        return userService.getSingleuser(username);
    }


}
