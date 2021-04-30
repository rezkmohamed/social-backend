package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentLikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.CommentLike;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudCommentLike;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {
	private CrudCommentLike commentLikeRepo;
	
	@Autowired
	public CommentLikeServiceImpl(CrudCommentLike commentLikeRepo) {
		this.commentLikeRepo = commentLikeRepo;
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
	public void addCommentLike(CommentLike commentLike) {
		commentLikeRepo.addCommentLike(commentLike);
	}

	@Override
	@Transactional
	public void deleteCommentLikeById(String idCommentLike) {
		commentLikeRepo.deleteCommentLikeById(idCommentLike);
	}

}
