package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.util.LinkedList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Message;

public class DTOMessageUtils {
	public static MessageDTO messageToDTO(Message message) {
		MessageDTO ris = new MessageDTO(message.getIdMessage(),
				message.getProfileSender().getIdProfile(), 
				message.getProfileReciver().getIdProfile(),
				message.getMessage(),
				message.getDateMillis(), message.isSeen());
		ris.setIdConversation(message.getConversation().getIdConversation());
		return ris;
	}
	
	public static List<MessageDTO> messageToDTO(List<Message> messages){
		List<MessageDTO> ris = new LinkedList<>();
		for(Message m : messages) {
			ris.add(messageToDTO(m));
		}
		
		return ris;
	}
}
