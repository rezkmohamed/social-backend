package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Notification;

public interface CrudNotification {	
	public List<NotificationDTO> getNotificationsForProfile(String idProfile);
	
	public void deleteCommentLikeNotification(String idProfileNotificator, String idPost, String comment);
	
	public void deleteFollowNotification(String idProfileNotificator, String idProfileToNotify);
		
	public String addNewNotification(Notification notification);
	
	public boolean setNotificationsAsSeenForProfile(String idProfile);
}
