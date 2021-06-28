package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
	CREATE TABLE `message` (
	  `id_message` varchar(45) NOT NULL,
	  `id_profile_sender` varchar(45) NOT NULL,
	  `id_profile_reciver` varchar(45) NOT NULL,
	  `message` varchar(3000) DEFAULT NULL,
	  `isseen` boolean,
	  `date_message` timestamp,
	  PRIMARY KEY (`id_message`),
	  FOREIGN KEY (`id_profile_sender`) REFERENCES
	  `profile` (`id_profile`) on delete cascade,
	  FOREIGN KEY (`id_profile_reciver`) REFERENCES
	  `profile` (`id_profile`) on delete cascade
	);
 *
 */
@Entity
@Table(name="messages")
public class Message {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_message")
	private String idMessage;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_profile_sender")
	private Profile profileSender;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_profile_reciver")
	private Profile profileReciver;
	
	@Column(name="message")
	private String message;
	
	@Column(name="date_message")
	private LocalDateTime date;
	
	@Column(name="isseen")
	private boolean isSeen;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_conversation")
	private Conversation conversation;
	
	// id conversation 
	
	public Message() {
		
	}

	public Message(String date, boolean isSeen) {
		this.isSeen = isSeen;
		int endOfString = 19;
		date = date.substring(0, endOfString);
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateInput = LocalDateTime.parse(date, formatter);
		this.date = dateInput;
	}

	public String getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}

	public Profile getProfileSender() {
		return profileSender;
	}

	public void setProfileSender(Profile profileSender) {
		this.profileSender = profileSender;
	}

	public Profile getProfileReciver() {
		return profileReciver;
	}

	public void setProfileReciver(Profile profileReciver) {
		this.profileReciver = profileReciver;
	}

	public LocalDateTime getDate() {
		return date;
	}
	
	public String getDateString() {
		return date.toString();
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isSeen() {
		return isSeen;
	}

	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", profileSender=" + profileSender + ", profileReciver="
				+ profileReciver + ", message=" + message + ", date=" + date + ", isSeen=" + isSeen + ", conversation="
				+ conversation + "]";
	}
}
