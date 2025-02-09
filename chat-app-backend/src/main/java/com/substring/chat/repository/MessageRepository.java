package com.substring.chat.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.substring.chat.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	@Query("select m from Room m where m.roomId = :roomId")
	Page<Message> findMessageByRoomId(@Param("roomId") Long roomId, PageRequest pageable);
}
