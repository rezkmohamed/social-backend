package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Message;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudMessage;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudProfile;

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
	public List<MessageDTO> getMessagesInterface(String idProfileLogged) {
		return messagesRepo.getMessagesInterface(idProfileLogged);
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

}
