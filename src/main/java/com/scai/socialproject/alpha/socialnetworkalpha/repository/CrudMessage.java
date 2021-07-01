package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ConversationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Conversation;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Message;

public interface CrudMessage {
	public List<MessageDTO> getAllMessages();
	
	public List<MessageDTO> getMessagesOfChat(String idConversation);
		
	public List<ConversationDTO> getConversationsForProfile(String idProfileLogged);
	
	public Conversation getConversation(String idConversation);
	
	public Conversation getConversation(String idProfile1, String idProfile2);
	
	public boolean setMessagesAsSeen(String idConversation, String idLoggedProfile);
	
	public String addMessage(Message message);
	
	public String createNewConversation(Conversation conversation);
}
