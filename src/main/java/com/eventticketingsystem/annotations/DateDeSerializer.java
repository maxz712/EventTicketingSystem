package com.eventticketingsystem.annotations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.time.Instant;
import java.util.Date;

public class DateDeSerializer extends StdDeserializer<Date> {

    public DateDeSerializer() { super(Date.class); }

    // Attempt to parse an ISO 8601 date. If we are unable to, we return nothing.
    // This is so we get validation error instead of deserialization error which will
    // hide all other validation errors.
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) {
        try {
            String value = p.readValueAs(String.class);
            return Date.from(Instant.parse(value));
        } catch (Exception e) {
            // Do nothing
        }
        return null;
    }
}
