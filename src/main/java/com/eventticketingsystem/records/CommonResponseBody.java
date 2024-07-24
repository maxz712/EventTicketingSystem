package com.eventticketingsystem.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommonResponseBody(String data, String pagination, @JsonProperty("error") ErrorResponse errorResponse) {
}
