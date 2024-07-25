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

        if (user.isEmpty()) {
            throw new NotFoundException("No Data Found for " + email);
        }


        return user.get();
    }

    public User updateUser(String email, UserRequest userRequest) {
        Optional<User> user = usersRepository.findByEmail(email);

        // Checks if user with the specific email already exists
        if (user.isEmpty()) {
            throw new NotFoundException("No Data Found for " + email);
        }

        // Update user name
        User usr = user.get();
        usr.setName(userRequest.getName());

        return usersRepository.save(usr);
    }

    public User createUser(UserRequest userRequest) {
        Optional<User> user = usersRepository.findByEmail(userRequest.getEmail());

        // Checks if user with the specific email already exists
        if (user.isPresent()) {
            throw new DataIntegrityViolationException(userRequest.getEmail() + " already exists.");
        }

        // Creates new user
        User usr = new User();
        usr.setEmail(userRequest.getEmail());
        usr.setName(userRequest.getName());

        return usersRepository.save(usr);
    }
}
