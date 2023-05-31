package com.test.nmt.service.room;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.nmt.model.room.RoomDTO;
import com.test.nmt.repository.RoomRepository;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomDTO getById(Long id) {
        RoomDTO dto = new RoomDTO();
        return dto.loadFromEntity(roomRepository.findById(id).orElse(null));
    }

    @Override
    public List<RoomDTO> getAll() {
        return roomRepository.findAll().stream().map(room -> new RoomDTO().loadFromEntity(room))
                .collect(Collectors.toList());
    }
}
