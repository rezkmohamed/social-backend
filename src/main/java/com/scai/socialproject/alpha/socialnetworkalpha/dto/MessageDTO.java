package com.scai.socialproject.alpha.socialnetworkalpha.dto;

import java.time.LocalDateTime;

public class MessageDTO {
	private String idMessage;
	private String idProfileSender;
	private String idProfileReciver;
	private LocalDateTime date;
	private boolean isSeen;
	
	public MessageDTO() {
	}

	public MessageDTO(String idMessage, String idProfileSender, String idProfileReciver, LocalDateTime date,
			boolean isSeen) {
		super();
		this.idMessage = idMessage;
		this.idProfileSender = idProfileSender;
		this.idProfileReciver = idProfileReciver;
		this.date = date;
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

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isSeen() {
		return isSeen;
	}

	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}

	@Override
	public String toString() {
		return "MessageDTO [idMessage=" + idMessage + ", idProfileSender=" + idProfileSender + ", idProfileReciver="
				+ idProfileReciver + ", date=" + date + ", isSeen=" + isSeen + "]";
	}
}
