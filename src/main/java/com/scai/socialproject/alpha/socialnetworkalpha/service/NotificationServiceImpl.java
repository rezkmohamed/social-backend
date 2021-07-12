package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
//		List<NotificationDTO> ris = new LinkedList<>();
//		
//		ris.addAll(notificationRepo.getNewFollowersNotificationForProfile(idProfile));
//		ris.addAll(notificationRepo.getNewLikesNotificationForProfile(idProfile));
//		ris.addAll(notificationRepo.getNewCommentsForProfile(idProfile));
//		ris.addAll(notificationRepo.getNewCommentLikesForProfile(idProfile));
//		
//		return ris.stream().sorted(Comparator.comparing(NotificationDTO::getDateMillis,
//				Comparator.reverseOrder()))
//		   .collect(Collectors.toList());
		return notificationRepo.getNotificationsForProfile(idProfile);
	}

	@Override
	@Transactional
	public boolean setNotificationsAsSeenForProfile(String idProfile) {
		return notificationRepo.setNotificationsAsSeenForProfile(idProfile);
	}

}
