package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

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
	private Date date;
	
	@Column(name="isseen")
	private boolean isSeen;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_conversation")
	private Conversation conversation;
		
	public Message() {
		
	}

	public Message(Long dateInMillis, boolean isSeen) {
		this.isSeen = isSeen;
		this.date = new Date(dateInMillis);
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
	
	public Long getDateMillis() {
		return date.getTime();
	}

	public void setDate(Date date) {
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
}
