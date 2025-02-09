package com.substring.chat.service.impl;

import java.awt.print.Pageable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.substring.chat.entity.Message;
import com.substring.chat.entity.Room;
import com.substring.chat.exception.EntityNotFoundException;
import com.substring.chat.repository.MessageRepository;
import com.substring.chat.repository.RoomRempository;
import com.substring.chat.service.RoomService;

@Service
public class ServiceImpl implements RoomService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private RoomRempository roomRepository;

	@Override
	public Room createRoom(@RequestBody Room room) {
		return roomRepository.save(room);
	}

	@Override
	public Room getRoomById(Long roomId) {
		return roomRepository.findByRoomId(roomId)
				.orElseThrow(() -> new EntityNotFoundException("Room with ID " + roomId + " not found"));
	}

	@Override
	public List<Message> findMessgeByServiceid(Long roomId, int page, int size) {
		if (!roomRepository.existsById(roomId)) {
			throw new EntityNotFoundException("Room with ID " + roomId + " does not exist");
		}
		PageRequest pageable = PageRequest.of(page, size);
		Page<Message> messagePage = messageRepository.findMessageByRoomId(roomId, pageable);

		return messagePage.getContent();

	}

	@Override
	public Message createMessage(String sender, String content, Long roomId) {
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new EntityNotFoundException("Room with Id " + roomId + "not found"));

		Message message = new Message(sender, content, room);
		return messageRepository.save(message);
	}

}
