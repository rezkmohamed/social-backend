package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentLikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.CommentLike;

public interface CrudCommentLike {
	public List<CommentLikeDTO> findAllCommentLikes();
	
	public List<CommentLikeDTO> findAllCommentLikesForComment(String idComment);
	
	public CommentLike findCommentLikeByIdCommentAndIdProfile(String idComment, String idProfile);
	
	public CommentLikeDTO findCommentLikeById(String idCommentLike);
	
	public void addCommentLike(CommentLike commentLike);
	
	public void deleteCommentLikeById(String idCommentLike);
}
