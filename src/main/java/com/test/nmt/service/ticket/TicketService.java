package com.test.nmt.service.ticket;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.nmt.model.ticket.TicketDTO;

@Service
public interface TicketService {
    public TicketDTO getByTicketID(Long id);

    public List<TicketDTO> getByUserID(Long id);

    public void createTicket(TicketDTO ticketDTO);
}
