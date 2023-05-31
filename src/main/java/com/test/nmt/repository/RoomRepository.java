package com.test.nmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.nmt.model.room.RoomsEntity;

@Repository
public interface RoomRepository extends JpaRepository<RoomsEntity, Long> {

}
