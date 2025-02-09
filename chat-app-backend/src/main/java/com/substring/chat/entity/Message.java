package com.substring.chat.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String sender;

	private String content;

	private LocalDateTime timeStamp;

	public Message(String sender, String content, Room room) {

		this.sender = sender;
		this.content = content;
		this.timeStamp = LocalDateTime.now();
		this.room = room;

	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roomId", nullable = false)
	@JsonBackReference
	private Room room;

}
