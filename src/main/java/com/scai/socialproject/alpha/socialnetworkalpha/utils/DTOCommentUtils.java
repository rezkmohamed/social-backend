package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.util.ArrayList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;

public class DTOCommentUtils {
	private static CommentDTO DTOFromComment(Comment comment) {
		return new CommentDTO(comment.getIdComment(), 
									    comment.getComment(), 
									    comment.getDate(), 
									    comment.getPost().getIdPost(), 
									    comment.getWriter().getIdProfile(),
									    comment.getWriter().getNickname());
	}
	
	public static List<CommentDTO> commentToDTO(List<Comment> comments){
		List<CommentDTO> ris = new ArrayList<>();
		for(Comment comment : comments) {
			CommentDTO tmp = DTOFromComment(comment);
			ris.add(tmp);
		}
		
		return ris;
	}
	
	public static CommentDTO commentToDTO(Comment comment) {
		return DTOFromComment(comment);
	}

}
