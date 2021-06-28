package com.scai.socialproject.alpha.socialnetworkalpha.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageDTO {
	private String idMessage;
	private String idProfileSender;
	private String idProfileReciver;
	private String message;
	private String idConversation;
	private LocalDateTime date;
	private boolean isSeen;
	
	public MessageDTO() {
	}
	
	public MessageDTO(String idMessage, String idProfileSender, String idProfileReciver, String message,
			String date, boolean isSeen) {
		super();
		this.idMessage = idMessage;
		this.idProfileSender = idProfileSender;
		this.idProfileReciver = idProfileReciver;
		this.message = message;
		int endOfString = 19;
		date = date.substring(0, endOfString);
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateInput = LocalDateTime.parse(date, formatter);
		this.date = dateInput;
		this.isSeen = isSeen;
	}

	public String getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}

	public String getIdProfileSender() {
		return idProfileSender;
	}

	public void setIdProfileSender(String idProfileSender) {
		this.idProfileSender = idProfileSender;
	}

	public String getIdProfileReciver() {
		return idProfileReciver;
	}

	public void setIdProfileReciver(String idProfileReciver) {
		this.idProfileReciver = idProfileReciver;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(String date) {
		int endOfString = 19;
		date = date.substring(0, endOfString);
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateInput = LocalDateTime.parse(date, formatter);
		this.date = dateInput;
	}

	public boolean isSeen() {
		return isSeen;
	}

	public void setIsSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getIdConversation() {
		return idConversation;
	}

	public void setIdConversation(String idConversation) {
		this.idConversation = idConversation;
	}

	@Override
	public String toString() {
		return "MessageDTO [idMessage=" + idMessage + ", idProfileSender=" + idProfileSender + ", idProfileReciver="
				+ idProfileReciver + ", message=" + message + ", idConversation=" + idConversation + ", date=" + date
				+ ", isSeen=" + isSeen + "]";
	}
}
