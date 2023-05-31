package com.test.nmt.service.ticket;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.nmt.model.ticket.TicketDTO;
import com.test.nmt.model.ticket.TicketsEntity;
import com.test.nmt.repository.TicketRepository;

@Service
public class TicketServiceImp implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public TicketDTO getByTicketID(Long id) {
        return new TicketDTO().loadFromEntity(ticketRepository.findById(id).orElse(null));
    }

    @Override
    public List<TicketDTO> getByUserID(Long id) {
        return ticketRepository.findAll().stream().filter(value -> value.getUserID().equals(id))
                .map(value -> new TicketDTO().loadFromEntity(value))
                .collect(Collectors.toList());
    }

    @Override
    public void createTicket(TicketDTO ticketDTO) {
        ticketRepository.save(new TicketsEntity().loadFromDTO(ticketDTO));
    }
}
