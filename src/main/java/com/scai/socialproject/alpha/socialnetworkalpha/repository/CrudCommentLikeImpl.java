package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentLikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.CommentLike;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOCommentsLikeUtils;

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
		List<CommentLikeDTO> commentLikesDTO = DTOCommentsLikeUtils.commentLikeToDTO(commentLikes);
		
		return commentLikesDTO;
	}

	@Override
	public List<CommentLikeDTO> findAllCommentLikesForComment(String idComment) {
		Session session = entityManager.unwrap(Session.class);
		Query<CommentLike> query = session.createQuery("from CommentLike where id_comment=:idComment");
		query.setParameter("idComment", idComment);
		List<CommentLike> commentLikes = query.getResultList();
		List<CommentLikeDTO> commentLikesDTO = DTOCommentsLikeUtils.commentLikeToDTO(commentLikes);
		
		return commentLikesDTO;
	}

	@Override
	public CommentLikeDTO findCommentLikeById(String idCommentLike) {
		Session session = entityManager.unwrap(Session.class);
		Query<CommentLike> query = session.createQuery("from CommentLike where id_comment_like=:idCommentLike");
		query.setParameter("idCommentLike", idCommentLike);
		CommentLike commentLike = query.getSingleResult();
		CommentLikeDTO commentLikeDTO = DTOCommentsLikeUtils.commentLikeToDTO(commentLike);
		
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

	@Override
	public CommentLike findCommentLikeByIdCommentAndIdProfile(String idComment, String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<CommentLike> query = session.createQuery("from CommentLike where id_comment = :idComment AND id_liker = :idProfile");
		query.setParameter("idComment", idComment); query.setParameter("idProfile", idProfile);

		try {
			CommentLike commentLike = query.getSingleResult();
			return commentLike;
		} catch (Exception e) {
			System.out.println("non ce il commentLike");
		}
		
		return null;
	}

	@Override
	public boolean deleteCommentLikeByIdCommentAndIdProfile(String idComment, String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from CommentLike where id_comment = :idComment AND id_liker = :idProfile");
		query.setParameter("idComment", idComment); query.setParameter("idProfile", idProfile);
				
		if(query.executeUpdate() == 1) {
			return true;
		}
		
		return false;
	}
}
