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
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOCommentUtils;

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
		List<CommentDTO> commentsDTO = DTOCommentUtils.commentToDTO(comments);
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
		List<CommentDTO> commentsDTO = DTOCommentUtils.commentToDTO(comments);
		findCommentLikes(session, commentsDTO);

				
		return commentsDTO;
	}
	
	@Override
	public CommentDTO addComment(CommentDTO commentDTO) {
		Session session = entityManager.unwrap(Session.class);
		Comment comment = new Comment(commentDTO.getComment(), commentDTO.getDate());
		Query<Post> queryPost = session.createQuery("from Post where id_post=:idPost");
		queryPost.setParameter("idPost", commentDTO.getIdPost());
		Post post = queryPost.getSingleResult();
		comment.setPost(post);
		Query<Profile> queryProfile = session.createQuery("from Profile where id_profile=:idProfile");
		queryProfile.setParameter("idProfile", commentDTO.getIdProfile());
		Profile profile = queryProfile.getSingleResult();
		comment.setWriter(profile);
		session.save(comment);
		
		return commentDTO;
	}
	
	@Override
	public void deleteComment(String idComment) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createQuery("delete from Comment where id_comment=:idComment");
		query.setParameter("idComment", idComment);
		query.executeUpdate();		
		
	}

	@Override
	public CommentDTO updateComment(CommentDTO commentDTO) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createQuery("update Comment set comment = :commentText where id_comment = :idComment");
		query.setParameter("commentText", commentDTO.getComment());
		query.setParameter("idComment", commentDTO.getIdComment());
		query.executeUpdate();
		
		return commentDTO;
	}

	@Override
	public Comment getCommentByIdComment(String idComment) {
		Session session = entityManager.unwrap(Session.class);
		Comment comment = session.get(Comment.class, idComment);
		
		return comment;
	}
}
