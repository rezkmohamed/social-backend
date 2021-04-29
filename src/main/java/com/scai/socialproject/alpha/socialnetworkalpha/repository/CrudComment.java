package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;

public interface CrudComment {
	public List<CommentDTO> findAllComments();
	
	public List<CommentDTO> findAllComments(String idPost);
	
	public void addComment(Comment comment);
	
	public void deleteComment(String idPost, String idProfile);
}
