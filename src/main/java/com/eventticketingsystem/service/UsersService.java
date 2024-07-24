package com.eventticketingsystem.service;

import com.eventticketingsystem.entity.UserRequest;
import com.eventticketingsystem.repository.UsersRepository;
import com.eventticketingsystem.entity.User;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public User fetchUserByEmail(String email) {
        Optional<User> user = usersRepository.findByEmail(email);
        if (user.isEmpty()){
            throw new NotFoundException("No Data Found for " + email);
        }
        return user.get();

    }

    public User createUser(UserRequest userRequest) {
        Optional<User> user = usersRepository.findByEmail(userRequest.getEmail());
        if (user.isPresent()) {
            throw new DataIntegrityViolationException(userRequest.getEmail() + " already exists.");
        }
        return user;
    }
}
