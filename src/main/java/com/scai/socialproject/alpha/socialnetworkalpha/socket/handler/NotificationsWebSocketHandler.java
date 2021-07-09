package com.scai.socialproject.alpha.socialnetworkalpha.socket.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class NotificationsWebSocketHandler extends TextWebSocketHandler{
	private final Map<String, WebSocketSession> webSocketSessionsMap = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.webSocketSessionsMap.put(UUID.randomUUID().toString(), session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		/**
		 * FIXME 
		 * ADD WEBSOCKET'S SEND MESSAGE LOGIC.
		 */
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		for(Map.Entry<String, WebSocketSession> entry : webSocketSessionsMap.entrySet()) {
			if(entry.getValue().equals(session)) {
				webSocketSessionsMap.remove(entry.getKey());
				return;
			}
		}
	}
}
