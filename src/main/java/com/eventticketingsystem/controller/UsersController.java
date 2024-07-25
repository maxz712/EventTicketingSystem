package com.eventticketingsystem.controller;

import com.eventticketingsystem.entity.User;
import com.eventticketingsystem.entity.UserRequest;
import com.eventticketingsystem.service.UsersService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/user")
@Validated
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable(value = "email") @NotBlank String email) {
        User user = usersService.fetchUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "email") @NotBlank String email,
                                           @Valid @RequestBody UserRequest userRequest) {
        User user = usersService.updateUser(email, userRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = usersService.createUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
