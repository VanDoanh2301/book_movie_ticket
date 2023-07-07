package com.test.nmt.repository;

import com.test.nmt.model.ticket.Ticket;
import com.test.nmt.model.ticket.TicketsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TicketFakeRepository extends JpaRepository<Ticket, Long> {
    List<Ticket>  findByUserName(String name);

    @Transactional
    @Modifying
    void deleteByTicketID(Long id);
}

