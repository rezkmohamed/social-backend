package com.scai.socialproject.alpha.socialnetworkalpha.socket.config;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.scai.socialproject.alpha.socialnetworkalpha.socket.handler.ChatWebSocketHandler;
import com.scai.socialproject.alpha.socialnetworkalpha.socket.handler.NotificationsWebSocketHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
	@Value("${chatEndpoint}")
	private String CHAT_ENDPOINT;
	@Value("${notificationsEndpoint}")
	private String NOTIFICATIONS_ENDPOINT;


	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry
		.addHandler(getChatWebSocketHandler(), CHAT_ENDPOINT)
		.setAllowedOrigins("*");
		
		registry
		.addHandler(getNotificationsWebSocketHandler(), NOTIFICATIONS_ENDPOINT)
		.setAllowedOrigins("*");
	}
	
	@Bean
	public WebSocketHandler getChatWebSocketHandler() {
		return new ChatWebSocketHandler();
	}	
	
	@Bean
	public WebSocketHandler getNotificationsWebSocketHandler() {
		return new NotificationsWebSocketHandler();
	}
}
