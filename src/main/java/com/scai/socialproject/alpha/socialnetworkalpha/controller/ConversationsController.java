package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ConversationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.service.MessageService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

@RestController
@RequestMapping("conversations")
public class ConversationsController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private RequestUtils requestUtils;
		
	@GetMapping("")
	public ResponseEntity<List<ConversationDTO>> getConversationsForProfile(HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		return new ResponseEntity<>(messageService.getConversationsForProfile(idProfile), HttpStatus.OK);
	}	
	
	@PostMapping("/new/{idSecondProfile}")
	public ResponseEntity<ConversationDTO> createNewConversation(@PathVariable String idSecondProfile, HttpServletRequest request){
		String idFirstProfile = requestUtils.idProfileFromToken(request);
		ConversationDTO conversation = messageService.getConversation(idFirstProfile, idSecondProfile);
		if(conversation != null) {
			return new ResponseEntity<>(conversation, HttpStatus.OK);
		}
		ConversationDTO newConversation = 
		messageService.createNewConversation(idFirstProfile, idSecondProfile);
		if(newConversation == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(newConversation, HttpStatus.OK);
	}
	
	@PostMapping("/addmessage/{idSecondProfile}")
	public ResponseEntity<HttpStatus> addNewMessage(@PathVariable String idSecondProfile,@RequestBody MessageDTO message ,HttpServletRequest request){
		if(messageService.addMessage(message)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}