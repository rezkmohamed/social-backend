package com.scai.socialproject.alpha.socialnetworkalpha.socket.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class ChatWebSocketHandler extends TextWebSocketHandler{
	private final Map<String, WebSocketSession> webSocketSessions = new HashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.webSocketSessions.put(UUID.randomUUID().toString(), session);
	}
	

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		if(message.toString().contains("token")) {
			System.out.println("token::::");
		}
		
	}
	

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		for(Map.Entry<String, WebSocketSession> entry : webSocketSessions.entrySet()) {
			if(entry.getValue().equals(session)) {
				webSocketSessions.remove(entry.getKey());
			}
		}
	}

}
