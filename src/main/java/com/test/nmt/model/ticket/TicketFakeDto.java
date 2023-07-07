package com.test.nmt.model.ticket;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketFakeDto {
    private String userName;
    private String movie;
    private String location;
    private String  showTimeID;
}
