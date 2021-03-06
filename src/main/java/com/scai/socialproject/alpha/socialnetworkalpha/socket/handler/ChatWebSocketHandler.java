package com.scai.socialproject.alpha.socialnetworkalpha.socket.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.MessageDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.service.MessageService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler{
	private final Map<String, WebSocketSession> webSocketSessions = new HashMap<>();
	@Autowired
	private RequestUtils requestUtils;
	@Autowired
	private MessageService messageService;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.webSocketSessions.put(UUID.randomUUID().toString(), session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		if(message.toString().contains("Bearer ")) {
			String idProfile = requestUtils.idProfileFromToken(message.getPayload().toString());
			for(Map.Entry<String, WebSocketSession> entry : webSocketSessions.entrySet()) {
				if(entry.getValue().equals(session)) {
					String key = entry.getKey();
					webSocketSessions.remove(key);
					webSocketSessions.put(idProfile, session);
					return;
				}
			}
			
			
			
		}
		else {
			String msg = message.getPayload();
			String idProfileSession = null;
			for(Map.Entry<String, WebSocketSession> entry : webSocketSessions.entrySet()) {
				
				if(entry.getValue().equals(session)) {
					idProfileSession = entry.getKey();
					ObjectMapper om = new ObjectMapper();
					MessageDTO msgDTO = om.readValue(msg, MessageDTO.class);
					msgDTO.setIdProfileSender(idProfileSession);
					MessageDTO msgToSend = messageService.addMessage(msgDTO);
					if(msgToSend != null) {
						if(webSocketSessions.containsKey(msgToSend.getIdProfileReciver())) {
							webSocketSessions.get(msgToSend.getIdProfileReciver()).sendMessage(message);
						}
					}
					break;
				}
			}
		}
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		for(Map.Entry<String, WebSocketSession> entry : webSocketSessions.entrySet()) {
			if(entry.getValue().equals(session)) {
				webSocketSessions.remove(entry.getKey());
				return;
			}
		}
	}
}
