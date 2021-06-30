package com.scai.socialproject.alpha.socialnetworkalpha.dto;

import java.util.Date;

public class MessageDTO {
	private String idMessage;
	private String idProfileSender;
	private String idProfileReciver;
	private String message;
	private String idConversation;
	private Date date;
	private boolean isSeen;
	
	public MessageDTO() {
	}
	
	public MessageDTO(String idMessage, String idProfileSender, String idProfileReciver, String message, Long date,
			boolean isSeen) {
		super();
		this.idMessage = idMessage;
		this.idProfileSender = idProfileSender;
		this.idProfileReciver = idProfileReciver;
		this.message = message;
		this.date = new Date(date);
		this.isSeen = isSeen;
	}

	public MessageDTO(String idMessage, String idProfileSender, String idProfileReciver, String message, Date date,
		boolean isSeen) {
		super();
		this.idMessage = idMessage;
		this.idProfileSender = idProfileSender;
		this.idProfileReciver = idProfileReciver;
		this.message = message;
		this.date = date;
		this.isSeen = isSeen;
	}
	
	public MessageDTO(String idMessage, String idProfileSender, String idProfileReciver, String message,
			String idConversation, Date date, boolean isSeen) {
		super();
		this.idMessage = idMessage;
		this.idProfileSender = idProfileSender;
		this.idProfileReciver = idProfileReciver;
		this.message = message;
		this.idConversation = idConversation;
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
	
	public Long getDateMillis() {
		return date.getTime();
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Long date) {
		this.date = new Date(date);
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
