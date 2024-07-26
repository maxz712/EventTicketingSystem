package com.eventticketingsystem.service;

import com.eventticketingsystem.entity.Event;
import com.eventticketingsystem.entity.EventRequest;
import com.eventticketingsystem.entity.EventResponse;
import com.eventticketingsystem.entity.User;
import com.eventticketingsystem.repository.EventsRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    public EventResponse getAllEvents() {
        List<Event> allEvents = eventsRepository.findAllUnexpiredEvents();

        if (allEvents.isEmpty()) {
            throw new NotFoundException("No Data found");
        }

        EventResponse eventResponse = new EventResponse();
        eventResponse.setEvents(allEvents);
        eventResponse.setCount(allEvents.size());

        return eventResponse;
    }

    public Event getEventByName(String name) {
        Optional<Event> event = eventsRepository.findByName(name);

        if (event.isEmpty()) {
            throw new NotFoundException("No Data Found for " + name);
        }

        return event.get();
    }

    public Event createEvent(@Valid EventRequest eventRequest) {
        Optional<Event> event = eventsRepository.findByName(eventRequest.getName());

        // Checks if event with the specific name already exists
        if (event.isPresent()) {
            throw new DataIntegrityViolationException(eventRequest.getName()+ " already exists.");
        }

        // Creates new event
        // Overrides expired events with the same name
        Event newEvent = new Event();
        newEvent.setName(eventRequest.getName());
        newEvent.setCreateTime(new Date());
        newEvent.setStartTime(eventRequest.getStartTime());
        newEvent.setEndTime(eventRequest.getEndTime());
        newEvent.setCurrentCapacity(eventRequest.getMaxCapacity());
        newEvent.setMaxCapacity(eventRequest.getMaxCapacity());

        return eventsRepository.save(newEvent);
    }
}
