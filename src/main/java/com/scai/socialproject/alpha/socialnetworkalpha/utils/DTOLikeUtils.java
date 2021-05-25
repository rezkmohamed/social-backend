package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.util.ArrayList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.LikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;

public class DTOLikeUtils {
	private static LikeDTO DTOFromLike(Like like) {
		return new LikeDTO(like.getId(),
								  like.getDate(),
								  like.getPost().getIdPost(),
								  like.getProfileLiker().getIdProfile());
	}
	
	public static List<LikeDTO> likeToDTO(List<Like> likes){
		List<LikeDTO> ris = new ArrayList<>();
		for(Like like : likes) {
			LikeDTO tmp = DTOFromLike(like);
			ris.add(tmp);
		}
		
		return ris;
	}
	
	public static LikeDTO likeToDTO(Like like) {
		return DTOFromLike(like);
	}

}
