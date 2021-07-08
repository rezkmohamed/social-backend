package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;

public interface CrudNotification {
	public List<NotificationDTO> getNotificationsForProfile(String idProfile);
	
	public List<NotificationDTO> getNewFollowersNotificationForProfile(String idProfile);
	
	public List<NotificationDTO> getNewLikesNotificationForProfile(String idProfile);
	
	public List<NotificationDTO> getNewCommentsForProfile(String idProfile);
	
	public List<NotificationDTO> getNewCommentLikesForProfile(String idProfile);
	
	public boolean setNotificationsAsSeenForProfile(String idProfile);
}
