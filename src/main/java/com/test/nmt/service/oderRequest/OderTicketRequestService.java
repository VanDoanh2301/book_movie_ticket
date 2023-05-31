package com.test.nmt.service.oderRequest;

import org.springframework.stereotype.Service;

import com.test.nmt.model.ticket.TicketDetailDTO;

@Service
public interface OderTicketRequestService {
    public TicketDetailDTO getTicketDetailByUser_TicketID(Long user, Long ticket);

    public TicketDetailDTO getTicketDetailByUserID(Long id);
}
