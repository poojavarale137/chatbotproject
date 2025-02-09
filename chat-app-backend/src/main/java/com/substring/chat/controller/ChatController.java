package com.substring.chat.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.substring.chat.entity.Message;
import com.substring.chat.entity.Room;
import com.substring.chat.payload.MessageRequest;
import com.substring.chat.repository.RoomRempository;

@RestController
@CrossOrigin("http://localhost:3000")
public class ChatController {

	@Autowired
	private RoomRempository roomRempository;

	@MessageMapping("/sendMessage/{roomId}")
	@SendTo("/topic/room/{roomId}")
	public Message sendMessage(@DestinationVariable Long roomId, @RequestBody MessageRequest request) {

		Room room = roomRempository.findById(request.getRoomId())
				.orElseThrow(() -> new RuntimeException("Room not found"));

		Message message = new Message();
		message.setContent(request.getContent());
		message.setSender(request.getSender());
		message.setTimeStamp(LocalDateTime.now());

		if (room != null) {
			room.getMessages().add(message);
			roomRempository.save(room);
		} else {
			throw new RuntimeException("Room not found");
		}
		return message;

	}

}
