package com.substring.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.substring.chat.entity.Message;
import com.substring.chat.entity.Room;
import com.substring.chat.exception.EntityNotFoundException;
import com.substring.chat.service.RoomService;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:3000")
public class RoomController {

	@Autowired
	private RoomService roomService;

	// create room
	@PostMapping
	public ResponseEntity<Object> createRoom(@RequestBody Room room) {
		Room savedRoom = roomService.createRoom(room);
		return ResponseEntity.ok(savedRoom);

	}

	@GetMapping("/{roomId}")
	public ResponseEntity<Room> getRoomById(@PathVariable("roomId") Long roomId) {
		Room room = roomService.getRoomById(roomId);
		return ResponseEntity.ok(room);
	}

	@PostMapping("/{roomId}/messages")
	public ResponseEntity<Message> createMessage(@PathVariable("roomId") Long roomId, @RequestBody Message message) {
		Message createdMessage = roomService.createMessage(message.getSender(), message.getContent(), roomId);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
	}

	@GetMapping("/{roomId}/messages")
	public ResponseEntity<List<Message>> getMessages(@PathVariable("roomId") Long roomId,
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size) {

		try {
			// Call the service to get paginated messages
			List<Message> messages = roomService.findMessgeByServiceid(roomId, page, size);
			return ResponseEntity.ok(messages);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

}
