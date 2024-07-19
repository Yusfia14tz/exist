package com.system.attendance.controller;

import com.system.attendance.auth.LoginRequest;
import com.system.attendance.model.User;
import com.system.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200") // Change the origin as per your Angular app's URL
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User saveUser = userService.addUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<String>("user deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")

    public ResponseEntity<User> updateUser(@PathVariable("id") long userId,
                                           @RequestBody User user) {
        user.setId(userId);
        User userUpdate = userService.updateUser(user);
        return new ResponseEntity<User>(userUpdate, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> user = userService.getAllUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}