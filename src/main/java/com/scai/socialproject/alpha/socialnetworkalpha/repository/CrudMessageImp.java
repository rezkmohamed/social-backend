package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.query.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ConversationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Conversation;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Message;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOConversationUtils;
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
	public List<MessageDTO> getMessagesOfChat(String idConversation) {
		Session session = entityManager.unwrap(Session.class);
		Query<Message> query = session
				.createQuery("from Message where id_conversation = :idConversation",
						Message.class);
		query.setParameter("idConversation", idConversation);
		List<Message> messages = query.getResultList();
		List<MessageDTO> messagesDTO = DTOMessageUtils.messageToDTO(messages);
		
		return messagesDTO;
	}

	@Override
	public String addMessage(Message message) {
		Session session = entityManager.unwrap(Session.class);
		session.save(message);
		
		return (String) session.save(message);
	}

	@Override
	public List<ConversationDTO> getConversationsForProfile(String idProfileLogged) {
		Session session = entityManager.unwrap(Session.class);
		Query<Conversation> query = session.createQuery("from Conversation where id_profile1 = :idProfile1 OR id_profile2 = :idProfile2");
		query.setParameter("idProfile1", idProfileLogged); query.setParameter("idProfile2", idProfileLogged);
		List<Conversation> conversations = query.getResultList();
		List<ConversationDTO> conversationsDTO = DTOConversationUtils.conversationToDTO(conversations);
		
		return conversationsDTO;
	}

	@Override
	public String createNewConversation(Conversation conversation) {
		Session session = entityManager.unwrap(Session.class);
		String idNewConversation = (String) session.save(conversation);
		if(!idNewConversation.equals("")) {
			return idNewConversation;
		}
		
		return null;
	}

	@Override
	public Conversation getConversation(String idConversation) {
		Session session = entityManager.unwrap(Session.class);
		Query<Conversation> query = session.createQuery("from Conversation where id_conversation = :idConv");
		query.setParameter("idConv", idConversation);
		try {
			Conversation conv = query.getSingleResult();
			return conv;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public Conversation getConversation(String idProfile1, String idProfile2) {
		Session session = entityManager.unwrap(Session.class);
		Query<Conversation> query = session.createQuery("from Conversation where id_profile1 = :idProfile1 AND id_profile2 = :idProfile2");
		query.setParameter("idProfile1", idProfile1); query.setParameter("idProfile2", idProfile2);
		try {
			Conversation conv = query.getSingleResult();
			return conv;
		} catch (Exception e) {
			Query<Conversation> query2 = session.createQuery("from Conversation where id_profile1 = :idProfile2 AND id_profile2 = :idProfile1");
			query2.setParameter("idProfile1", idProfile1); query2.setParameter("idProfile2", idProfile2);
			try {
				Conversation conv = query2.getSingleResult();
				return conv;
			} catch (Exception e2) {
			}
		}
		return null;
	}

	@Override
	public boolean setMessagesAsSeen(String idConversation) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("update Message set isseen = 1 where id_conversation = :idConversation");
		query.setParameter("idConversation", idConversation);
		int ris = query.executeUpdate();
		if(ris < 1) {
			return false;
		}
		
		return true;
	}
}
