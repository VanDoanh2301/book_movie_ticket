package com.test.nmt.model.ticket;

import java.util.List;

import com.test.nmt.model.scheduleRequest.MovieScheduleRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDetailByUserDTO {
    private Long ticketID;
    private List<MovieScheduleRequestDTO> showTimeDTO;
}
