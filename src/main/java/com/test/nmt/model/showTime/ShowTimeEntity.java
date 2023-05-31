package com.test.nmt.model.showTime;

import java.sql.Date;
import java.sql.Time;

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
@Table(name = "showtime")
public class ShowTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showTimeID;
    private Long movieID;
    private Long roomID;
    private Long numTicketSold;
    private Date showDate;
    private Time startTime;
    private Time endTime;

    public ShowTimeEntity loadFromDTO(ShowTimeDTO dto) {
        this.showTimeID = dto.getShowTimeID();
        this.movieID = dto.getMovieID();
        this.roomID = dto.getRoomID();
        this.numTicketSold = dto.getNumTicketSold();
        this.showDate = dto.getShowDate();
        this.startTime = dto.getStartTime();
        this.endTime = dto.getEndTime();
        return this;
    }
}
