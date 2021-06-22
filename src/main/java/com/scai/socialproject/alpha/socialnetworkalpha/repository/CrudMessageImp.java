package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;

@Repository
public class CrudMessageImp implements CrudMessage {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<MessageDTO> getAllMessages() {
		Session session = entityManager.unwrap(Session.class);
		
		return null;
	}

	@Override
	public List<MessageDTO> getMessagesOfChat(String idProfileLogged, String idOtherProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageDTO> getMessagesInterface() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addMessage(MessageDTO message) {
		// TODO Auto-generated method stub
		return false;
	}

}
