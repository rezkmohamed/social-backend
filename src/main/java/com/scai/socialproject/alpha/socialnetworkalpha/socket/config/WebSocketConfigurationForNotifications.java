package com.scai.socialproject.alpha.socialnetworkalpha.socket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.scai.socialproject.alpha.socialnetworkalpha.socket.handler.NotificationsWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfigurationForNotifications implements WebSocketConfigurer{
	@Value("${notificationsEndpoint}")
	private String NOTIFICATIONS_ENDPOINT;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {	
		registry
		.addHandler(getNotificationsWebSocketHandler(), NOTIFICATIONS_ENDPOINT)
		.setAllowedOrigins("*");
	}
	
	@Bean
	public WebSocketHandler getNotificationsWebSocketHandler() {
		return new NotificationsWebSocketHandler();
	}

}
