package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.util.ArrayList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentLikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.CommentLike;

public class DTOCommentsLikeUtils {
	private static CommentLikeDTO DTOFromCommentLike(CommentLike commentLike) {
		return new CommentLikeDTO(commentLike.getIdCommentLike(), 
												commentLike.getComment().getIdComment(), 
												commentLike.getProfile().getIdProfile());
	}

	
	public static List<CommentLikeDTO> commentLikeToDTO(List<CommentLike> commentLikes){
		List<CommentLikeDTO> ris = new ArrayList<>();
		for(CommentLike commentLike : commentLikes) {
			CommentLikeDTO tmp = DTOFromCommentLike(commentLike);
			ris.add(tmp);
		}		
		
		return ris;
	}
	
	public static CommentLikeDTO commentLikeToDTO(CommentLike commentLike) {
		return DTOFromCommentLike(commentLike);
	}

}
