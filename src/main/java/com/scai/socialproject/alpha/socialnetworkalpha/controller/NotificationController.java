package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.service.NotificationService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

@RestController
@RequestMapping("notifications")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private RequestUtils requestUtils;
	
	@GetMapping("")
	public ResponseEntity<List<NotificationDTO>> getNewNotifications(HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		return new ResponseEntity<>(notificationService.getNotificationsForProfile(idProfile), HttpStatus.OK);
	}
	
	@PutMapping("/setseen")
	public ResponseEntity<HttpStatus> setNewNotificationsAsSeen(HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		notificationService.setNotificationsAsSeenForProfile(idProfile);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
