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
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudProfile;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {
	private CrudCommentLike commentLikeRepo;
	private CrudComment commentRepo;
	private CrudProfile profileRepo;
	
	@Autowired
	public CommentLikeServiceImpl(CrudCommentLike commentLikeRepo, CrudComment commentRepo, CrudProfile profileRepo) {
		this.commentLikeRepo = commentLikeRepo;
		this.commentRepo = commentRepo;
		this.profileRepo = profileRepo;
	}

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

}
