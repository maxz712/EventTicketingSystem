package com.eventticketingsystem.controller;


import com.eventticketingsystem.TicketsTestHelper;
import com.eventticketingsystem.entity.TicketRequest;
import com.eventticketingsystem.service.EventsService;
import com.eventticketingsystem.service.TicketsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import jakarta.ws.rs.NotFoundException;
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

@WebMvcTest(TicketsController.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockBean(JpaMetamodelMappingContext.class)
public class TicketsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketsService ticketsService;

    private static MockedStatic<ExecutionContext> context;

    @BeforeAll
    void initStaticMocks() {
        context = Mockito.mockStatic(ExecutionContext.class);
    }

    @AfterAll
    static void cleanup() { context.close(); }

    @Test
    @DisplayName("Get All Tickets By Email, Success | 200")
    public void getTicketsByEmail() throws Exception {
        this.mockMvc.perform(get("/ticket")
                        .queryParam("email", "test1@test.com"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get All Tickets By Email, No Email | 400")
    public void getTicketsByEmailNoEmail() throws Exception {
        this.mockMvc.perform(get("/ticket")
                        .queryParam("email", ""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Get All Tickets By Email, Wrong Path | 404")
    public void getTicketsByEmailWrongPath() throws Exception {
        this.mockMvc.perform(get("/ticket/")
                        .queryParam("email", "test1@test.com"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Create Ticket, Success | 201")
    public void createTicket() throws Exception {
        TicketRequest ticketRequest = TicketsTestHelper.generateTicketRequest();

        this.mockMvc.perform(
                        post("/ticket")
                                .content(asJsonString(ticketRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Create Ticket, Wrong Path | 404")
    public void createTicketWrongPath() throws Exception {
        TicketRequest ticketRequest = TicketsTestHelper.generateTicketRequest();

        this.mockMvc.perform(
                        post("/ticket/")
                                .content(asJsonString(ticketRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Create Ticket, Null Event Name | 400")
    public void createTicketNullEventName() throws Exception {
        TicketRequest ticketRequest = TicketsTestHelper.generateTicketRequest();
        ticketRequest.setEventName(null);

        this.mockMvc.perform(
                        post("/ticket")
                                .content(asJsonString(ticketRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Create Ticket, No Event | 404")
    public void createTicketNoEvent() throws Exception {
        TicketRequest ticketRequest = TicketsTestHelper.generateTicketRequest();

        Mockito.doThrow(new NotFoundException("Event Not Found")).when(ticketsService).createTicket(ticketRequest);

        this.mockMvc.perform(
                        post("/ticket")
                                .content(asJsonString(ticketRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());
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
