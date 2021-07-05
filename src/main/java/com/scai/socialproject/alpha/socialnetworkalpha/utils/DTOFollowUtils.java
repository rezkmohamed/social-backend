package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.util.ArrayList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;

public class DTOFollowUtils {
	private static FollowDTO DTOFromFollow(Follow follow) {
		FollowDTO tmp = new FollowDTO(follow.getIdFollow(),
									  follow.getFollower().getIdProfile(), 
									  follow.getFollowed().getIdProfile());
		return tmp;
	}
	
	public static List<FollowDTO> followToDTO(List<Follow> follows){
		List<FollowDTO> ris = new ArrayList<>();
		for(Follow follow : follows) {
			FollowDTO tmp = DTOFromFollow(follow);
			ris.add(tmp);
		}
		
		return ris;
	}
	
	public static FollowDTO followToDTO(Follow follow) {
		FollowDTO ris = DTOFromFollow(follow);
		return ris;
	}
}
