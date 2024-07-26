package com.eventticketingsystem.entity;

import com.eventticketingsystem.annotations.ValidEventDates;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@ValidEventDates
public class EventRequest {

    @NotNull
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @NotNull
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @NotNull
    private Date endTime;

    @DecimalMin(value = "1")
    @DecimalMax(value = "5000000")
    @NotNull
    private Integer maxCapacity;
}
