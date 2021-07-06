package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;

public interface NotificationService {
	public List<NotificationDTO> getNotificationsForProfile(String idProfile);
	
	public boolean setNotificationsAsSeenForProfile(String idProfile);
}
