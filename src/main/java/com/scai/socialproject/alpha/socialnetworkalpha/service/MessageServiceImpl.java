package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ConversationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Conversation;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Message;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudMessage;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudProfile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOConversationUtils;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private CrudMessage messagesRepo;
	@Autowired
	private CrudProfile profilesRepo;

	@Override
	@Transactional
	public List<MessageDTO> getAllMessages() {
		return messagesRepo.getAllMessages();
	}

	@Override
	@Transactional
	public List<MessageDTO> getMessagesOfChat(String idProfileLogged, String idOtherProfile) {
		return messagesRepo.getMessagesOfChat(idProfileLogged, idOtherProfile);
	}

	@Override
	@Transactional
	public boolean addMessage(MessageDTO message) {
		Profile profile1 = profilesRepo.findProfile(message.getIdProfileSender());
		if(profile1 == null) {
			return false;
		}
		Profile profile2 = profilesRepo.findProfile(message.getIdProfileReciver());
		if(profile2 == null) {
			return false;
		}
		Message msg = new Message(message.getDate().toString(), message.isSeen());
		msg.setProfileSender(profile1);
		msg.setProfileReciver(profile2);
		messagesRepo.addMessage(msg);
		
		return false;
	}

	@Override
	public List<ConversationDTO> getConversationsForProfile(String idProfileLogged) {
		return messagesRepo.getConversationsForProfile(idProfileLogged);
	}

	@Override
	public ConversationDTO createNewConversation(String idFirstProfile, String idSecondProfile) {
		Profile firstProfile = profilesRepo.findProfile(idFirstProfile);
		if(firstProfile == null) {
			return null;
		}
		Profile secondProfile = profilesRepo.findProfile(idSecondProfile);
		if(secondProfile == null) {
			return null;
		}
		Conversation conversation = new Conversation();
		conversation.setFirstProfile(firstProfile); conversation.setSecondProfile(secondProfile);
		String idConversation = messagesRepo.createNewConversation(conversation);
		ConversationDTO ris = DTOConversationUtils.conversationToDTO(conversation);
		ris.setIdConversation(idConversation);
		
		return ris;
	}

}
