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
@Table(name = "EVENTS")
public class Event {

    @Id
    @JsonIgnore
    @org.hibernate.annotations.Generated(event = EventType.INSERT)
    @Column(name = "ID")
    private Long id;

    @NaturalId
    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "END_TIME")
    private Date endTime;

    @Column(name = "CURRENT_CAPACITY")
    private Integer currentCapacity;

    @NaturalId
    @Column(name = "MAX_CAPACITY")
    private Integer maxCapacity;
}
