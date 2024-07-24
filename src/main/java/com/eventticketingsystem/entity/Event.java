package com.eventticketingsystem.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.generator.EventType;

import java.util.Date;

@Entity
@Data
@Table(name = "EVENT")
public class Event {
    @Id
    @org.hibernate.annotations.Generated(event = EventType.INSERT)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "EVENT_DATE")
    private Date eventDate;

    @Column(name = "CURRENT_CAPACITY")
    private Integer currentCapacity;

    @Column(name = "MAX_CAPACITY")
    private Integer maxCapacity;
}
