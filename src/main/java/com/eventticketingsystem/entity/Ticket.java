package com.eventticketingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.hibernate.generator.EventType;

import java.util.Date;

public class Ticket {

    @Id
    @org.hibernate.annotations.Generated(event = EventType.INSERT)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EVENT_ID")
    private Long eventId;

    @Column(name = "EVENT_NAME")
    private String eventName;

    @Column(name = "EVENT_DATE")
    private Date eventDate;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "EMAIL")
    private String email;
}
