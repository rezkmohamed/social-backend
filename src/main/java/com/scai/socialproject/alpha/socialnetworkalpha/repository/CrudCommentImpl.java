package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOutils;

@Repository
public class CrudCommentImpl implements CrudComment{
	private EntityManager entityManager;

	@Autowired
	public CrudCommentImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<CommentDTO> findAllComments() {
		Session session = entityManager.unwrap(Session.class);
		Query<Comment> query = session.createQuery("from Comment");
		List<Comment> comments = query.getResultList();
		List<CommentDTO> commentsDTO = DTOutils.commentToDTO(comments);
		
		return commentsDTO;
	}

	@Override
	public List<CommentDTO> findAllComments(String idPost) {
		Session session = entityManager.unwrap(Session.class);
		Query<Comment> query = session.createQuery("from Comment where id_post=:idPost");
		query.setParameter("idPost", idPost);
		List<Comment> comments = query.getResultList();
		List<CommentDTO> commentsDTO = DTOutils.commentToDTO(comments);
				
		return commentsDTO;
	}
	
	@Override
	public void addComment(Comment comment) {
		Session session = entityManager.unwrap(Session.class);
		session.save(comment);
	}
	
	@Override
	public void deleteComment(String idPost, String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createQuery("delete from Comment where id_post=:idPost AND id_writer=:idProfile");
		query.setParameter("idPost", idPost); query.setParameter("idProfile", idProfile);
		query.executeUpdate();		
		
	}

	
	
}
