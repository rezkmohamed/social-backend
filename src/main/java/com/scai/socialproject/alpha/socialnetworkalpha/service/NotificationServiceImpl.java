package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudNotification;

@Service
public class NotificationServiceImpl implements NotificationService{
	@Autowired
	private CrudNotification notificationRepo;
	
	@Override
	@Transactional
	public List<NotificationDTO> getNotificationsForProfile(String idProfile) {
		return notificationRepo.getNotificationsForProfile(idProfile);
	}

	@Override
	@Transactional
	public boolean setNotificationsAsSeenForProfile(String idProfile) {
		return notificationRepo.setNotificationsAsSeenForProfile(idProfile);
	}

}
