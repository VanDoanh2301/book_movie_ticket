package com.test.nmt.model.ticket;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketID;
    private Long userID;
    private Long showTimeID;

    public TicketDTO loadFromEntity(TicketsEntity entity) {
        this.ticketID = entity.getTicketID();
        this.userID = entity.getUserID();
        this.showTimeID = entity.getShowTimeID();
        return this;
    }
}
