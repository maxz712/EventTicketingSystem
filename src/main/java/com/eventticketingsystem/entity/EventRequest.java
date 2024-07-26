package com.eventticketingsystem.entity;

import com.eventticketingsystem.annotations.DateDeSerializer;
import com.eventticketingsystem.annotations.ValidEventDates;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@ValidEventDates
public class EventRequest {

    @Size(min = 1, max = 1000)
    @NotNull
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonDeserialize(using = DateDeSerializer.class)
    @NotNull
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonDeserialize(using = DateDeSerializer.class)
    @NotNull
    private Date endTime;

    @DecimalMin(value = "1")
    @DecimalMax(value = "5000000")
    @NotNull
    private Integer maxCapacity;
}
