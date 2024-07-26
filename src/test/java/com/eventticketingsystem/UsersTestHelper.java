package com.eventticketingsystem;

import com.eventticketingsystem.entity.UserRequest;

public class UsersTestHelper {
    public static UserRequest generateUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("test1");
        userRequest.setEmail("test1@test.com");
        return userRequest;
    }
}
