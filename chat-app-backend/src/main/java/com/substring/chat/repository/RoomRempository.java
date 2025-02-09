package com.substring.chat.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.substring.chat.entity.Message;
import com.substring.chat.entity.Room;

@Repository
public interface RoomRempository extends JpaRepository<Room, Long> {

	@Query(" select r from Room r where r.id= :id")
	Optional<Room> findByRoomId(@Param("id") Long roomId); // custom query to find room by its id

}
