package com.eventticketingsystem.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {

    @NotNull
    private String name;

    @NotNull
    private String email;
}
