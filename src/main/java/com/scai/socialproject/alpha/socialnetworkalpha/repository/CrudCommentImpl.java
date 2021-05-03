package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.CommentLike;
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
		findCommentLikes(session, commentsDTO);
		
		return commentsDTO;
	}

	private void findCommentLikes(Session session, List<CommentDTO> commentsDTO) {
		for(CommentDTO commentDTO : commentsDTO) {
			Query<CommentLike> query2 = session
					.createQuery("from CommentLike where id_comment=:idComment");
			query2.setParameter("idComment", commentDTO.getIdComment());
			List<CommentLike> commentLikes = query2.getResultList();
			if(commentLikes != null) {
				commentDTO.setCommentLikesCounter(commentLikes.size());
				commentDTO.setCommentlikes(commentLikes);
			}
			else {
				commentDTO.setCommentLikesCounter(0);
			}
		}
	}

	@Override
	public List<CommentDTO> findAllComments(String idPost) {
		Session session = entityManager.unwrap(Session.class);
		Query<Comment> query = session.createQuery("from Comment where id_post=:idPost");
		query.setParameter("idPost", idPost);
		List<Comment> comments = query.getResultList();
		List<CommentDTO> commentsDTO = DTOutils.commentToDTO(comments);
		findCommentLikes(session, commentsDTO);

				
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
