package com.eventticketingsystem.repository;

import com.eventticketingsystem.entity.User;
import com.eventticketingsystem.constants.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {

    @Query(value = QueryConstants.SELECT_USER_BY_EMAIL, nativeQuery = true)
    Optional<User> findByEmail(@Param("email") final String email);
}
