package com.test.nmt.model.ticket;

import java.util.List;

import com.test.nmt.model.scheduleRequest.MovieScheduleRequestDTO;
import com.test.nmt.model.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDetailDTO {
    private Long ticketID;
    private UserDTO userDTO;
    private List<MovieScheduleRequestDTO> showTimeDTO;

}
