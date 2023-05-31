package com.test.nmt.model.ticket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class TicketsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketID;
    private Long userID;
    private Long showTimeID;

    public TicketsEntity loadFromDTO(TicketDTO dto) {
        this.ticketID = dto.getTicketID();
        this.userID = dto.getUserID();
        this.showTimeID = dto.getShowTimeID();
        return this;
    }
}
