package com.test.nmt.api.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.nmt.model.room.RoomDTO;
import com.test.nmt.service.room.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping
    public List<RoomDTO> getAllMovie() {
        return roomService.getAll();
    }
}
