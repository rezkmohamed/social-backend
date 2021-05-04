package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;

public interface CommentService {
	public List<CommentDTO> findAllComments();
	
	public List<CommentDTO> findAllComments(String idPost);
	
	public CommentDTO addComment(CommentDTO commentDTO);
	
	public CommentDTO updateComment(CommentDTO commentDTO);

	public void deleteComment(String idComment);
}
