package com.websocket.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebScConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub");
        //메시지 송신
        registry.enableSimpleBroker("/sub");
        //메시지 수신
    
        
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp").withSockJS();
        //.setAllowedOrigins("*");
        //위 메서드 추가했을 경우 아래 에러가 발생, 와일드 카드가 아니라 도메인을 추가해서 추후 진행사항 파악
        //"When allowCredentials is true, allowedOrigins cannot contain the special value \"*\"
    }
}
