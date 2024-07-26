package com.eventticketingsystem.service;

import com.eventticketingsystem.entity.*;
import com.eventticketingsystem.repository.EventsRepository;
import com.eventticketingsystem.repository.TicketsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketsService {

    @Autowired
    EventsRepository eventsRepository;

    @Autowired
    TicketsRepository ticketsRepository;

    public TicketResponse getTicketsByUserName(String email) {
        List<Ticket> tickets = ticketsRepository.findByEmail(email);

        if (tickets.isEmpty()) {
            throw new NotFoundException("No Data found for " + email);
        }

        // Put all the tickets for the user in a response object
        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTickets(tickets);
        ticketResponse.setCount(tickets.size());

        return ticketResponse;
    }

    @Transactional
    public Ticket createTicket(@Valid TicketRequest ticketRequest) {
        Optional<Event> event = eventsRepository.findAvailableByName(ticketRequest.getEventName());

        if (event.isEmpty()) {
            throw new NotFoundException("No Data found for " + ticketRequest.getEventName());
        }
        // Update current capacity the ticket is for
        Event evnt = event.get();
        evnt.setCurrentCapacity(evnt.getCurrentCapacity() - 1);
        eventsRepository.save(evnt);

        // Create new ticket for the requested event and user
        Ticket ticket = new Ticket();
        ticket.setEventName(ticketRequest.getEventName());
        ticket.setUserName(ticketRequest.getUserName());
        ticket.setEmail(ticketRequest.getEmail());

        ticket.setStartTime(evnt.getStartTime());
        ticket.setEndTime(evnt.getEndTime());

        return ticketsRepository.save(ticket);
    }
}
