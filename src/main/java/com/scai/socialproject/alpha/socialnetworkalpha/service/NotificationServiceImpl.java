package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationTypeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Notification;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudNotification;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudPost;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudProfile;

@Service
public class NotificationServiceImpl implements NotificationService{
	@Autowired
	private CrudNotification notificationRepo;
	@Autowired
	private CrudProfile profileRepo;
	@Autowired
	private CrudPost postRepo;
	
	@Override
	@Transactional
	public List<NotificationDTO> getNotificationsForProfile(String idProfile) {
		return notificationRepo.getNotificationsForProfile(idProfile);
	}

	@Override
	@Transactional
	public NotificationDTO addNotification(NotificationDTO notification) {
		Notification notif = new Notification(profileRepo.findProfile(notification.getIdProfileNotificator()),
				profileRepo.findProfile(notification.getIdProfileToNotify()),
				false, notification.getNotificationType());
		if(notification.getNotificationType() != NotificationTypeDTO.FOLLOW) {
			notif.setPost(postRepo.findPostEntityById(notification.getIdPost()));
			if(notification.getNotificationType() != NotificationTypeDTO.LIKE) {
				notif.setComment(notification.getComment());
			}
		}
		if(notificationRepo.addNewNotification(notif) != null) {
			return notification;
		}
		
		return null;
	}
	
	@Override
	@Transactional
	public boolean setNotificationsAsSeenForProfile(String idProfile) {
		return notificationRepo.setNotificationsAsSeenForProfile(idProfile);
	}
}
