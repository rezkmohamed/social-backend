package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ConversationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Conversation;

public interface MessageService {
	public List<MessageDTO> getAllMessages();
	
	public List<MessageDTO> getMessagesOfChat(String idProfileLogged, String idOtherProfile);
	
	public List<ConversationDTO> getConversationsForProfile(String idProfileLogged);
	
	public ConversationDTO getConversation(String idProfile1, String idProfile2);
	
	public boolean addMessage(MessageDTO message);
	
	public boolean addMessage(String message);
	
	/**
	 * returns ConversationDTO with the generated id of the new conversation
	 * @param idFirstProfile
	 * @param idSecondProfile
	 * @return
	 */
	public ConversationDTO createNewConversation(String idFirstProfile, String idSecondProfile);
}
