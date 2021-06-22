package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.query.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Message;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOMessageUtils;

@Repository
public class CrudMessageImp implements CrudMessage {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<MessageDTO> getAllMessages() {
		Session session = entityManager.unwrap(Session.class);
		Query<Message> query = session.createQuery("from Message", Message.class);
		List<Message> messages = query.getResultList();
		List<MessageDTO> messagesDTO = DTOMessageUtils.messageToDTO(messages);
		
		return messagesDTO;
	}

	@Override
	public List<MessageDTO> getMessagesOfChat(String idProfileLogged, String idOtherProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Message> query = session
				.createQuery("from Message where id_profile_sender = :idProfileLogged AND id_profile_reciver = :idOtherProfile",
						Message.class);
		query.setParameter("idProfileLogged", idProfileLogged);
		query.setParameter("idOtherProfile", idOtherProfile);
		List<Message> messages = query.getResultList();
		List<MessageDTO> messagesDTO = DTOMessageUtils.messageToDTO(messages);
		
		return messagesDTO;
	}

	@Override
	public List<MessageDTO> getMessagesInterface(String idProfileLogged) {
		Session session = entityManager.unwrap(Session.class);
		Query<Message> query = session
				.createQuery("from Message where id_profile_reciver = :idProfileLogged ORDER BY date_message DESC");
		query.setParameter("idProfileLogged", idProfileLogged);
		query.setFirstResult(0);
		query.setMaxResults(20);
		
		List<Message> messages = query.getResultList();
		List<MessageDTO> messagesDTO = DTOMessageUtils.messageToDTO(messages);
		
		
		return messagesDTO;
	}

	@Override
	public boolean addMessage(Message message) {
		Session session = entityManager.unwrap(Session.class);
		session.save(message);
		
		return true;
	}

}
