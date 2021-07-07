package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationTypeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;

public class DTONotificationUtils {
	public static NotificationDTO DTONoficationFromFollow(Follow follow) {
		NotificationDTO ris = new NotificationDTO(DTOProfileUtils.profileToDTO(follow.getFollower()),
				DTOProfileUtils.profileToDTO(follow.getFollowed()), NotificationTypeDTO.FOLLOW, follow.getDateMillis());
		ris.setSeen(follow.isSeen());
		return ris;
	}
	
	public static List<NotificationDTO> DTONotificationFromFollow(List<Follow> follows, ImgUtils imgUtils){
		List<NotificationDTO> ris = new ArrayList<>();
		for(Follow follow : follows) {
			NotificationDTO tmp = DTONoficationFromFollow(follow);
			if(tmp.getProfileNotificator().getProPic() != null) {
				try {
					tmp.getProfileNotificator().setProPic(imgUtils.fileImgToBase64Encoding(tmp.getProfileNotificator().getProPic()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			ris.add(tmp);
		}
		
		return ris;
	}
}
