package com.eventticketingsystem.controller;

import com.eventticketingsystem.entity.Ticket;
import com.eventticketingsystem.entity.TicketRequest;
import com.eventticketingsystem.entity.TicketResponse;
import com.eventticketingsystem.service.TicketsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@Validated
public class TicketsController {

    @Autowired
    TicketsService ticketsService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody TicketRequest ticketRequest) {
        return new ResponseEntity<>(ticketsService.createTicket(ticketRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TicketResponse> getTicket(@RequestParam(value = "email") @NotBlank @Size(min = 1, max = 1000) String email) {
        return new ResponseEntity<>(ticketsService.getTicketsByUserName(email), HttpStatus.OK);
    }
}
