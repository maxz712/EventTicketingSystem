package com.eventticketingsystem.entity;

import lombok.Data;

import java.util.List;

@Data
public class TicketResponse {

    List<Ticket> tickets;

    Integer count;
}
