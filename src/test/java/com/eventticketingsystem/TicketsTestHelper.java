package com.eventticketingsystem;

import com.eventticketingsystem.entity.TicketRequest;

public class TicketsTestHelper {
    public static TicketRequest generateTicketRequest() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setEmail("test1@test.com");
        ticketRequest.setUserName("test1");
        ticketRequest.setEventName("test1");
        return ticketRequest;
    }
}
