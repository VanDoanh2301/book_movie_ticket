package com.test.nmt.model.room;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class RoomsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomID;
    private String roomName;
    private String location;
    private Integer numSit;

    public RoomsEntity loadFromDTO(RoomDTO dto) {
        this.roomID = dto.getRoomID();
        this.roomName = dto.getRoomName();
        this.location = dto.getLocation();
        this.numSit = dto.getNumberSit();
        return this;
    }
}
