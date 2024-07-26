package com.eventticketingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.hibernate.generator.EventType;

import java.util.Date;

@Entity
@Data
@Table(name = "TICKETS")
public class Ticket {

    @Id
    @JsonIgnore
    @org.hibernate.annotations.Generated(event = EventType.INSERT)
    @Column(name = "ID")
    private Long id;

    @NaturalId
    @Column(name = "EVENT_NAME")
    private String eventName;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "END_TIME")
    private Date endTime;

    @Column(name = "USER_NAME")
    private String userName;

    @NaturalId
    @Column(name = "EMAIL")
    private String email;
}
