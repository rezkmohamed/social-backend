package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Message;

public interface MessageService {
	public List<MessageDTO> getAllMessages();
	
	public List<MessageDTO> getMessagesOfChat(String idProfileLogged, String idOtherProfile);
	
	public List<MessageDTO> getMessagesInterface(String idProfileLogged);
	
	public boolean addMessage(MessageDTO message);

}
