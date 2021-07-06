package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.util.ArrayList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationTypeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;

public class DTONotificationUtils {
	public static NotificationDTO DTONoficationFromFollow(Follow follow) {
		return new NotificationDTO(DTOProfileUtils.profileToDTO(follow.getFollower()),
				DTOProfileUtils.profileToDTO(follow.getFollowed()), NotificationTypeDTO.FOLLOW);
	}
	
	public static List<NotificationDTO> DTONotificationFromFollow(List<Follow> follows){
		List<NotificationDTO> ris = new ArrayList<>();
		for(Follow follow : follows) {
			NotificationDTO tmp = DTONoficationFromFollow(follow);
			ris.add(tmp);
		}
		
		return ris;
	}
}
