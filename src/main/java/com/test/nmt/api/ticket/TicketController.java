package com.test.nmt.api.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.nmt.model.ticket.TicketDTO;
import com.test.nmt.service.showTime.ShowTimeService;
import com.test.nmt.service.ticket.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ShowTimeService showTimeService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public void createTicket(@RequestBody TicketDTO ticketDTO) {
        ticketService.createTicket(ticketDTO);
        showTimeService.increaseTicketSold(ticketDTO.getShowTimeID());
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public void updateTicket(@RequestBody TicketDTO ticketDTO) {
        ticketService.createTicket(ticketDTO);
    }
}
