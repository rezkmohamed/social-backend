package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.service.MessageService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

@RestController
public class MessageController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private MessageService messageService;
	@Autowired
	private RequestUtils requestUtils;

	
	@MessageMapping("/chat/{to}")
	public ResponseEntity<HttpStatus> sendMessage(@DestinationVariable String to, MessageDTO message, HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);

		message.setIdProfileSender(idProfile);
		
		if(!messageService.addMessage(message)) {
			simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
