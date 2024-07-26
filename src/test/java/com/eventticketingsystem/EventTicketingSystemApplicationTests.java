package com.eventticketingsystem;

import com.eventticketingsystem.controller.EventsController;
import com.eventticketingsystem.controller.TicketsController;
import com.eventticketingsystem.controller.UsersController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@ContextConfiguration(initializers = {YamlTestPropertyOverrideContextInitializer.class})
class EventTicketingSystemApplicationTests {

	@Autowired
	EventsController eventsController;

	@Autowired
	TicketsController ticketsController;

	@Autowired
	UsersController usersController;

	@Test
	@DisplayName("Verify Application Context")
	void contextLoads() {
		assertThat(eventsController).isNotNull();
		assertThat(ticketsController).isNotNull();
		assertThat(usersController).isNotNull();
	}

}
