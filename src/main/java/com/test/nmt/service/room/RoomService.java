package com.test.nmt.service.room;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.nmt.model.room.RoomDTO;

@Service
public interface RoomService {
    public RoomDTO getById(Long id);

    public List<RoomDTO> getAll();
}
