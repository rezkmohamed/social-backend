package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;

public interface NotificationService {
	public List<NotificationDTO> getNotificationsForProfile(String idProfile);
	
	public NotificationDTO addNotification(NotificationDTO notification);
	
	public boolean setNotificationsAsSeenForProfile(String idProfile);
}
