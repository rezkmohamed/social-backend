package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ConversationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;

public interface MessageService {
	public List<MessageDTO> getAllMessages();
	
	public List<MessageDTO> getMessagesOfChat(String idConversation);
	
	public List<ConversationDTO> getConversationsForProfile(String idProfileLogged);
	
	public ConversationDTO getConversation(String idProfile1, String idProfile2);
	
	public boolean addMessage(MessageDTO message);
	
	public MessageDTO addMessage(String message);
	
	/**
	 * returns ConversationDTO with the generated id of the new conversation
	 * @param idFirstProfile
	 * @param idSecondProfile
	 * @return
	 */
	public ConversationDTO createNewConversation(String idFirstProfile, String idSecondProfile);
	
	public boolean setMessagesAsSeen(String idConversation, String idLoggedProfile);
}
