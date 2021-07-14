package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentLikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.CommentLike;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudComment;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudCommentLike;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudNotification;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudProfile;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {
	@Autowired
	private CrudCommentLike commentLikeRepo;
	@Autowired
	private CrudComment commentRepo;
	@Autowired
	private CrudProfile profileRepo;
	@Autowired
	private CrudNotification notificationsRepo;


	@Override
	@Transactional
	public List<CommentLikeDTO> findAllCommentLikes() {
		return commentLikeRepo.findAllCommentLikes();
	}

	@Override
	@Transactional
	public List<CommentLikeDTO> findAllCommentLikesForComment(String idComment) {
		return commentLikeRepo.findAllCommentLikesForComment(idComment);
	}

	@Override
	@Transactional
	public CommentLikeDTO findCommentLikeById(String idCommentLike) {
		return commentLikeRepo.findCommentLikeById(idCommentLike);
	}

	@Override
	@Transactional
	public boolean addCommentLike(String idComment, String idProfile) {
		CommentLike commentLike = new CommentLike();
		Comment comment = commentRepo.getCommentByIdComment(idComment);
		if(comment == null) {
			return false;
		}
		commentLike.setComment(comment);
		Profile profile = profileRepo.findProfile(idProfile);
		if(profile == null) {
			return false;
		}
		commentLike.setProfile(profile);
		commentLikeRepo.addCommentLike(commentLike);
		
		return true;
	}

	@Override
	@Transactional
	public void deleteCommentLikeById(String idCommentLike) {
		commentLikeRepo.deleteCommentLikeById(idCommentLike);
	}

	@Override
	@Transactional
	public boolean isLiked(String idComment, String idProfile) {
		CommentLike liked = commentLikeRepo.findCommentLikeByIdCommentAndIdProfile(idComment, idProfile);
		if(liked == null) {
			return false;
		}
		else if(liked.getProfile().getIdProfile().equals(idProfile) && 
				liked.getComment().getIdComment().equals(idComment)) {
			return true;
		}
					
		return false;
	}

	@Override
	@Transactional
	public boolean deleteCommentLike(String idComment, String idProfile) {
		CommentLike commentLike = commentLikeRepo.findCommentLikeByIdCommentAndIdProfile(idComment, idProfile);
		/**
		 * FIXME:
		 * REMOVE NOTIFICATION.
		 */
		String idPost = commentLike.getComment().getPost().getIdPost();
		notificationsRepo.deleteCommentLikeNotification(idProfile, idPost, commentLike.getComment().getComment());
		
		return commentLikeRepo.deleteCommentLikeByIdCommentAndIdProfile(idComment, idProfile);
	}

}
