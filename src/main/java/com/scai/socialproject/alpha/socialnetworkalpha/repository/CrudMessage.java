package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;

public interface CrudMessage {
	public List<MessageDTO> getAllMessages();
	
	public List<MessageDTO> getMessagesOfChat(String idProfileLogged, String idOtherProfile);
	
	public List<MessageDTO> getMessagesInterface();
	
	public boolean addMessage(MessageDTO message);
}
