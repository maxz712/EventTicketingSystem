package com.eventticketingsystem.entity;


import lombok.Data;

import java.util.List;

@Data
public class EventResponse {

    List<Event> events;

    Integer count;
}
