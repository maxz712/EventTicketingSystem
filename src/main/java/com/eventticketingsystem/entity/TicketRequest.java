package com.eventticketingsystem.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TicketRequest {

    @Size(min = 1, max = 1000)
    @NotNull
    private String eventName;

    @Size(min = 1, max = 1000)
    @NotNull
    private String userName;

    @Size(min = 1, max = 1000)
    @NotNull
    private String email;
}
