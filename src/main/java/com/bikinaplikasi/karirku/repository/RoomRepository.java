package com.bikinaplikasi.karirku.repository;

import com.bikinaplikasi.karirku.entity.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findAll();

    Room findRoomByNo(String no);
}
