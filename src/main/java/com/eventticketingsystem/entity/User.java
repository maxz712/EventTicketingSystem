package com.eventticketingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.hibernate.generator.EventType;

@Entity
@Data
@Table(name = "USERS")
public class User {

    @Id
    @JsonIgnore
    @org.hibernate.annotations.Generated(event = EventType.INSERT)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @NaturalId
    @Column(name = "EMAIL")
    private String email;
}
