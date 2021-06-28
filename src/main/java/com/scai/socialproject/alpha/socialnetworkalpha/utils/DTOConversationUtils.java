package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.util.LinkedList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ConversationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Conversation;

public class DTOConversationUtils {
	public static ConversationDTO conversationToDTO(Conversation conversation) {
		ConversationDTO ris = new ConversationDTO();
		ris.setIdConversation(conversation.getIdConversation());
		ris.setFirstProfile(DTOProfileUtils.profileToDTO(conversation.getFirstProfile()));
		ris.setSecondProfile(DTOProfileUtils.profileToDTO(conversation.getSecondProfile()));	
		if(conversation.getMessages() != null) {
			ris.setMessages(DTOMessageUtils.messageToDTO(conversation.getMessages()));
		}
		
		return ris;
	}
	
	public static List<ConversationDTO> conversationToDTO(List<Conversation> conversations){
		List<ConversationDTO> ris = new LinkedList<>();
		for(Conversation c : conversations) {
			ris.add(conversationToDTO(c));
		}
		
		return ris;
	}
}
