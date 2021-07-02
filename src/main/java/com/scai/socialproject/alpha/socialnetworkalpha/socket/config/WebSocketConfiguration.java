package com.scai.socialproject.alpha.socialnetworkalpha.socket.config;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.scai.socialproject.alpha.socialnetworkalpha.socket.handler.ChatWebSocketHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
	@Value("${chatEndpoint}")
	private String CHAT_ENDPOINT;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(getChatWebSocketHandler(), CHAT_ENDPOINT).setAllowedOrigins("*");
	}
	
	@Bean
	public WebSocketHandler getChatWebSocketHandler() {
		return new ChatWebSocketHandler();
	}	
}
