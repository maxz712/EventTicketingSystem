package com.eventticketingsystem;

import com.eventticketingsystem.entity.EventRequest;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class EventsTestHelper {
    public static EventRequest generateEventRequest() {
        Date now = new Date();
        Date startTime = DateUtils.addDays(now, 1);
        Date endTime = DateUtils.addYears(now, 2);

        EventRequest eventRequest = new EventRequest();
        eventRequest.setStartTime(startTime);
        eventRequest.setEndTime(endTime);
        eventRequest.setName("test1");
        eventRequest.setMaxCapacity(200);

        return eventRequest;
    }
}
