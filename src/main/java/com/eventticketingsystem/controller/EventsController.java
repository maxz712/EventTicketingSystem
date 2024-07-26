package com.eventticketingsystem.controller;


import com.eventticketingsystem.entity.Event;
import com.eventticketingsystem.entity.EventRequest;
import com.eventticketingsystem.entity.EventResponse;
import com.eventticketingsystem.entity.User;
import com.eventticketingsystem.service.EventsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@Validated
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping
    public ResponseEntity<EventResponse> getAllEvents() {
        return new ResponseEntity<>(eventsService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Event> getEventByName(@PathVariable @NotBlank String name) {
        return new ResponseEntity<>(eventsService.getEventByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody EventRequest eventRequest) {
        return new ResponseEntity<>(eventsService.createEvent(eventRequest), HttpStatus.CREATED);
    }


}
