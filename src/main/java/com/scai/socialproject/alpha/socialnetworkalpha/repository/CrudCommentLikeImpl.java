package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentLikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.CommentLike;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOutils;

@Repository
public class CrudCommentLikeImpl implements CrudCommentLike {
	private EntityManager entityManager;
	
	@Autowired
	public CrudCommentLikeImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<CommentLikeDTO> findAllCommentLikes() {
		Session session = entityManager.unwrap(Session.class);
		Query<CommentLike> query = session.createQuery("from CommentLike", CommentLike.class);
		List<CommentLike> commentLikes = query.getResultList();
		List<CommentLikeDTO> commentLikesDTO = DTOutils.commentLikeToDTO(commentLikes);
		
		return commentLikesDTO;
	}

	@Override
	public List<CommentLikeDTO> findAllCommentLikesForComment(String idComment) {
		Session session = entityManager.unwrap(Session.class);
		Query<CommentLike> query = session.createQuery("from CommentLike where id_comment=:idComment");
		query.setParameter("idComment", idComment);
		List<CommentLike> commentLikes = query.getResultList();
		List<CommentLikeDTO> commentLikesDTO = DTOutils.commentLikeToDTO(commentLikes);
		
		return commentLikesDTO;
	}

	@Override
	public CommentLikeDTO findCommentLikeById(String idCommentLike) {
		Session session = entityManager.unwrap(Session.class);
		Query<CommentLike> query = session.createQuery("from CommentLike where id_comment_like=:idCommentLike");
		query.setParameter("idCommentLike", idCommentLike);
		CommentLike commentLike = query.getSingleResult();
		CommentLikeDTO commentLikeDTO = DTOutils.commentLikeToDTO(commentLike);
		
		return commentLikeDTO;
	}

	@Override
	public void addCommentLike(CommentLike commentLike) {
		Session session = entityManager.unwrap(Session.class);
		session.save(commentLike);
	}

	@Override
	public void deleteCommentLikeById(String idCommentLike) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from CommentLike where id_comment_like=:idCommentLike");
		query.setParameter("idCommentLike", idCommentLike);
		query.executeUpdate();
	}
}
