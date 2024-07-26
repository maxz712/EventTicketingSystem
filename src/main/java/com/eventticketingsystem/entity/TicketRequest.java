package com.eventticketingsystem.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

@Data
public class TicketRequest {

    @NotNull
    private String eventName;

    @NotNull
    private String userName;

    @NotNull
    private String email;
}
