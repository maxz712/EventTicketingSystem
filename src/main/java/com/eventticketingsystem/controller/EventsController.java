package com.eventticketingsystem.controller;


import com.eventticketingsystem.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@Validated
public class EventsController {

    @GetMapping("/")
    public ResponseEntity<>

}
