package com.eventticketingsystem.controller;

import com.eventticketingsystem.UsersTestHelper;
import com.eventticketingsystem.entity.UserRequest;
import com.eventticketingsystem.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.hibernate.sql.exec.ExecutionException;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockBean(JpaMetamodelMappingContext.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    private static MockedStatic<ExecutionContext> context;

    @BeforeAll
    void initStaticMocks() {
        context = Mockito.mockStatic(ExecutionContext.class);
    }

    @AfterAll
    static void cleanup() { context.close(); }

    @Test
    @DisplayName("Get User by Email, Success | 200")
    public void getUserByEmail() throws Exception {
        this.mockMvc.perform(get("/user/test1@test.com"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get User by Email, No Email | 404")
    public void getUserByEmailNoEmail() throws Exception {
        this.mockMvc.perform(get("/user/"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Create User, Success | 201")
    public void createUser() throws Exception {
        UserRequest userRequest = UsersTestHelper.generateUserRequest();

        this.mockMvc.perform(
                        post("/user")
                        .content(asJsonString(userRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Create User, Wrong Path | 404")
    public void createUserWrongPath() throws Exception {
        UserRequest userRequest = UsersTestHelper.generateUserRequest();

        this.mockMvc.perform(
                        post("/user/")
                                .content(asJsonString(userRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Create User, Null name | 400")
    public void createUserNullName() throws Exception {
        UserRequest userRequest = UsersTestHelper.generateUserRequest();
        userRequest.setName(null);

        this.mockMvc.perform(
                        post("/user")
                                .content(asJsonString(userRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Create User, Duplicate Email | 409")
    public void createUserDuplicateEmail() throws Exception {
        UserRequest userRequest = UsersTestHelper.generateUserRequest();

        Mockito.doThrow(new DataIntegrityViolationException("Already Exists")).when(usersService).createUser(userRequest);

        this.mockMvc.perform(
                        post("/user")
                                .content(asJsonString(userRequest))
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
