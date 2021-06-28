package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ConversationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Conversation;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Message;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudMessage;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudProfile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOConversationUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private CrudMessage messagesRepo;
	@Autowired
	private CrudProfile profilesRepo;
	@Autowired
	private RequestUtils requestUtils;

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
	@Transactional
	public boolean addMessage(String message) {
		System.out.println(message);
		ObjectMapper om = new ObjectMapper();
		try {
			MessageDTO msgDTO = om.readValue(message, MessageDTO.class);
			System.out.println(msgDTO);
			String idProfile1 = requestUtils.idProfileFromToken(msgDTO.getIdProfileSender());
			System.out.println(idProfile1);
			Profile profile1 = profilesRepo.findProfile(idProfile1);
			if(profile1 == null) {
				return false;
			}
			Profile profile2 = profilesRepo.findProfile(msgDTO.getIdProfileReciver());
			if(profile2 == null) {
				return false;
			}
			Message msg = new Message(msgDTO.getDate().toString(), msgDTO.isSeen());
			msg.setProfileSender(profile1);
			msg.setProfileReciver(profile2);
			/**
			 * FIXME
			 * ADD CONVERSATION
			 */
			Conversation conversation = messagesRepo.getConversation("1");
			msg.setConversation(conversation);
			messagesRepo.addMessage(msg);

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	@Transactional
	public List<ConversationDTO> getConversationsForProfile(String idProfileLogged) {
		return messagesRepo.getConversationsForProfile(idProfileLogged);
	}

	@Override
	@Transactional
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
		
		Conversation conv = messagesRepo.getConversation("");
		
		conversation.setFirstProfile(firstProfile); conversation.setSecondProfile(secondProfile);
		String idConversation = messagesRepo.createNewConversation(conversation);
		ConversationDTO ris = DTOConversationUtils.conversationToDTO(conversation);
		ris.setIdConversation(idConversation);
		
		return ris;
	}

	@Override
	public ConversationDTO getConversation(String idProfile1, String idProfile2) {
		Conversation conversation = messagesRepo.getConversation(idProfile1, idProfile2);
		if(conversation == null) {
			return null;
		}
		
		return DTOConversationUtils.conversationToDTO(conversation);
	}

}
