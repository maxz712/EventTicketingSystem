package com.eventticketingsystem.repository;

import com.eventticketingsystem.constants.QueryConstants;
import com.eventticketingsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Ticket, Integer> {

    @Query(value = QueryConstants.SELECT_UNEXPIRED_TICKET_BY_EMAIL, nativeQuery = true)
    List<Ticket> findByEmail(String email);
}
