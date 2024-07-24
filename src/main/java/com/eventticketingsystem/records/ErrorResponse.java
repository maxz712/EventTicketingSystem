package com.eventticketingsystem.records;

import java.util.List;

public record ErrorResponse(List<Error> errors) {
}
