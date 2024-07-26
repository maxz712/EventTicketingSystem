package com.eventticketingsystem.controller;


import com.eventticketingsystem.EventsTestHelper;
import com.eventticketingsystem.entity.EventRequest;
import com.eventticketingsystem.service.EventsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.hibernate.sql.exec.spi.ExecutionContext;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventsController.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockBean(JpaMetamodelMappingContext.class)
public class EventsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventsService eventsService;

    private static MockedStatic<ExecutionContext> context;

    @BeforeAll
    void initStaticMocks() {
        context = Mockito.mockStatic(ExecutionContext.class);
    }

    @AfterAll
    static void cleanup() { context.close(); }

    @Test
    @DisplayName("Get All Events, Success | 200")
    public void getAllEvents() throws Exception {
        this.mockMvc.perform(get("/event"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get All Events, Wrong Path | 404")
    public void getAllEventsWrongPath() throws Exception {
        this.mockMvc.perform(get("/event/"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Get Event By Name, Success | 200")
    public void getEventByName() throws Exception {
        this.mockMvc.perform(get("/event/test1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Create Event, Success | 201")
    public void createEvent() throws Exception {
        EventRequest eventRequest = EventsTestHelper.generateEventRequest();

        this.mockMvc.perform(
                        post("/event")
                                .content(asJsonString(eventRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Create Event, Wrong Path | 404")
    public void createEventWrongPath() throws Exception {
        EventRequest eventRequest = EventsTestHelper.generateEventRequest();

        this.mockMvc.perform(
                        post("/event/")
                                .content(asJsonString(eventRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Create Event, Null name | 400")
    public void createEventNullName() throws Exception {
        EventRequest eventRequest = EventsTestHelper.generateEventRequest();
        eventRequest.setName(null);

        this.mockMvc.perform(
                        post("/event")
                                .content(asJsonString(eventRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Create Event, Duplicate Name | 409")
    public void createEventDuplicateName() throws Exception {
        EventRequest eventRequest = EventsTestHelper.generateEventRequest();

        Mockito.doThrow(new DataIntegrityViolationException("Already Exists")).when(eventsService).createEvent(any());

        this.mockMvc.perform(
                        post("/event")
                                .content(asJsonString(eventRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isConflict());
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
