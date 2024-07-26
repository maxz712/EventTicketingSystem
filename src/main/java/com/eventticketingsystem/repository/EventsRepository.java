package com.eventticketingsystem.repository;


import com.eventticketingsystem.constants.QueryConstants;
import com.eventticketingsystem.entity.Event;
import com.eventticketingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface EventsRepository extends JpaRepository<Event, Integer> {

    @Query(value = QueryConstants.SELECT_UNEXPIRED_EVENTS, nativeQuery = true)
    List<Event> findAllUnexpiredEvents();

    @Query(value = QueryConstants.SELECT_EVENT_BY_NAME, nativeQuery = true)
    Optional<Event> findByName(String name);

    @Query(value = QueryConstants.SELECT_AVAILABLE_EVENT_BY_NAME, nativeQuery = true)
    Optional<Event> findAvailableByName(String name);

}
