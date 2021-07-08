package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;

public interface CrudComment {
	public List<CommentDTO> findAllComments();
	
	public List<CommentDTO> findAllComments(String idPost);
	
	public List<CommentDTO> findAllCommentsForProfile(String idProfile);
	
	public Comment getCommentByIdComment(String idComment);
	
	public CommentDTO addComment(CommentDTO commentDTO);
	
	public CommentDTO updateComment(CommentDTO commentDTO);
	
	public void deleteComment(String idComment);
}
