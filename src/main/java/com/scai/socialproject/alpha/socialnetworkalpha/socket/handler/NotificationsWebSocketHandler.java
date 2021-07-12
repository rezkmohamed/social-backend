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
import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.service.MessageService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.NotificationService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

@Component
public class NotificationsWebSocketHandler extends TextWebSocketHandler{
	private final Map<String, WebSocketSession> webSocketSessionsMap = new HashMap<>();
	@Autowired
	private RequestUtils requestUtils;
	@Autowired
	private NotificationService notificationService;
	
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
		if(message.toString().contains("Bearer ")) {
			String idProfile = requestUtils.idProfileFromToken(message.getPayload().toString());
			for(Map.Entry<String, WebSocketSession> entry : webSocketSessionsMap.entrySet()) {
				if(entry.getValue().equals(session)) {
					String key = entry.getKey();
					webSocketSessionsMap.remove(key);
					webSocketSessionsMap.put(idProfile, session);
					return;
				}
			}
		}
		else {
			String notification = message.getPayload();
			String idProfileSession = null;
			for(Map.Entry<String, WebSocketSession> entry : webSocketSessionsMap.entrySet()) {
				if(entry.getValue().equals(session)) {
					idProfileSession = entry.getKey();
					ObjectMapper om = new ObjectMapper();
					NotificationDTO notificationDTO = om.readValue(notification, NotificationDTO.class);
					notificationDTO.setIdProfileNotificator(idProfileSession);
					NotificationDTO notificationToSend = notificationService.addNotification(notificationDTO);
					if(notificationToSend != null) {
						if(webSocketSessionsMap.containsKey(notificationToSend.getIdProfileToNotify())) {
							webSocketSessionsMap.get(notificationToSend.getIdProfileToNotify()).sendMessage(message);
						}
					}
					break;
				}
			}
		}
		
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
