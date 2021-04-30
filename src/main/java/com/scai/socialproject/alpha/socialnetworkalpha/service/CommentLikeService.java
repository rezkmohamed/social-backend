package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentLikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.CommentLike;

public interface CommentLikeService {
	public List<CommentLikeDTO> findAllCommentLikes();
	
	public List<CommentLikeDTO> findAllCommentLikesForComment(String idComment);
	
	public CommentLikeDTO findCommentLikeById(String idCommentLike);
	
	public void addCommentLike(CommentLike commentLike);
	
	public void deleteCommentLikeById(String idCommentLike);

}
