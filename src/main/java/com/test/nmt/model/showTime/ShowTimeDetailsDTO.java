package com.test.nmt.model.showTime;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimeDetailsDTO {
    private Long showTimeID;
    private String roomName;
    private String location;
    private Integer numberSit;
    private Long numTicketSold;
    private Date showDate;
    private Time startTime;
    private Time endTime;
}
