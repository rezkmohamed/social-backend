package com.scai.socialproject.alpha.socialnetworkalpha.dto;

import java.util.List;

public class ConversationDTO {
	private String idConversation;
	private ProfileDTO firstProfile;
	private ProfileDTO secondProfile;
	private String latestMessage;
	private List<MessageDTO> messages;
	
	public ConversationDTO(){}

	public String getIdConversation() {
		return idConversation;
	}

	public void setIdConversation(String idConversation) {
		this.idConversation = idConversation;
	}

	public ProfileDTO getFirstProfile() {
		return firstProfile;
	}

	public void setFirstProfile(ProfileDTO firstProfile) {
		this.firstProfile = firstProfile;
	}

	public ProfileDTO getSecondProfile() {
		return secondProfile;
	}

	public void setSecondProfile(ProfileDTO secondProfile) {
		this.secondProfile = secondProfile;
	}

	public String getLatestMessage() {
		return latestMessage;
	}

	public void setLatestMessage(String latestMessage) {
		this.latestMessage = latestMessage;
	}

	public List<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}
}
