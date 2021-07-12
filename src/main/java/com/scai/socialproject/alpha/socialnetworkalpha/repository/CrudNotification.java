package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Notification;

public interface CrudNotification {	
	public List<NotificationDTO> getNotificationsForProfile(String idProfile);
		
	public String addNewNotification(Notification notification);
	
	public boolean setNotificationsAsSeenForProfile(String idProfile);
}
