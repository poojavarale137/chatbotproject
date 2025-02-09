package com.substring.chat.service;

import java.util.List;

import com.substring.chat.entity.Message;
import com.substring.chat.entity.Room;

public interface RoomService {

	Room getRoomById(Long roomId);

	List<Message> findMessgeByServiceid(Long roomId, int page, int size);

	Room createRoom(Room room);

	Message createMessage(String sender, String content, Long roomId);

}
