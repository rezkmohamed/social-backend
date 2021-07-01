package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ConversationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Conversation;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Message;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudMessage;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudProfile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOConversationUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.ImgUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private CrudMessage messagesRepo;
	@Autowired
	private CrudProfile profilesRepo;
	@Autowired
	private RequestUtils requestUtils;
	@Autowired
	private ImgUtils imgUtils;

	@Override
	@Transactional
	public List<MessageDTO> getAllMessages() {
		return messagesRepo.getAllMessages();
	}

	@Override
	@Transactional
	public List<MessageDTO> getMessagesOfChat(String idConversation) {
		List<MessageDTO> ris = messagesRepo.getMessagesOfChat(idConversation);
		ris = ris.stream().sorted(Comparator.comparing(MessageDTO::getDate,
				Comparator.reverseOrder()
				)).collect(Collectors.toList());
		return ris;
	}

	@Override
	@Transactional
	public MessageDTO addMessage(MessageDTO message) {
		Profile profile1 = profilesRepo.findProfile(message.getIdProfileSender());
		Profile profile2 = profilesRepo.findProfile(message.getIdProfileReciver());
		if(profile2 == null) {
			return null;
		}
		Message msg = new Message(message.getDateMillis(), false);
		msg.setMessage(message.getMessage());
		msg.setProfileSender(profile1);
		msg.setProfileReciver(profile2);
		
		Conversation conversation = messagesRepo.getConversation(message.getIdConversation());
		msg.setConversation(conversation);
		String newIdMsg = messagesRepo.addMessage(msg);
		message.setIdMessage(newIdMsg);
		messagesRepo.addMessage(msg);
		
		return message;
	}

	@Override
	@Transactional
	public List<ConversationDTO> getConversationsForProfile(String idProfileLogged) {
		List<ConversationDTO> ris = messagesRepo.getConversationsForProfile(idProfileLogged);
		for(ConversationDTO conv : ris) {
			conv.setMessages( conv.getMessages().stream().sorted(Comparator.comparing(
					MessageDTO::getDate,
					Comparator.reverseOrder()
					))
					.collect(Collectors.toList())
					);
			if(conv.getMessages().size() > 0) {
				conv.setLatestMessage(conv.getMessages().get(0).getMessage());
			}
			
			if(conv.getFirstProfile().getProPic() != null ) {
				try {
					conv.getFirstProfile().setProPic(imgUtils.fileImgToBase64Encoding(conv.getFirstProfile().getProPic()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(conv.getSecondProfile().getProPic() != null) {
				try {
					conv.getSecondProfile().setProPic(imgUtils.fileImgToBase64Encoding(conv.getSecondProfile().getProPic()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return ris;
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
				
		conversation.setFirstProfile(firstProfile); conversation.setSecondProfile(secondProfile);
		String idConversation = messagesRepo.createNewConversation(conversation);
		ConversationDTO ris = DTOConversationUtils.conversationToDTO(conversation);
		ris.setIdConversation(idConversation);
		
		return ris;
	}

	@Override
	@Transactional
	public ConversationDTO getConversation(String idProfile1, String idProfile2) {
		Conversation conversation = messagesRepo.getConversation(idProfile1, idProfile2);
		if(conversation == null) {
			return null;
		}
		
		return DTOConversationUtils.conversationToDTO(conversation);
	}

	@Override
	@Transactional
	public boolean setMessagesAsSeen(String idConversation, String idLoggedProfile) {
		Conversation conversation = messagesRepo.getConversation(idConversation);
		if(!conversation.getFirstProfile().getIdProfile().equals(idLoggedProfile) && 
				!conversation.getSecondProfile().getIdProfile().equals(idLoggedProfile)) {
			return false;
		}
		
		return messagesRepo.setMessagesAsSeen(idConversation, idLoggedProfile);
	}

}
