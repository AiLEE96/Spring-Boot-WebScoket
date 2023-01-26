package com.gmb.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker // 간단한 기능을 갖춘 메시지 브로커
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    // 웹 소켓 연결을 위한 STOMP Sub/Pub 엔드포인트 설정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // STOMP 접속 주소 url => /ws-stomp
        registry.addEndpoint("/ws-stomp") // 연결 될 엔드포인트
                .withSockJS(); // SocketJS 연결하는 설정
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지를 구독하는 요청 url => 메시지 받을 때
        registry.enableSimpleBroker("/sub");

        // 메시지를 발행하는 요청 url => 메시지 보낼 때
        registry.setApplicationDestinationPrefixes("/pub");
    }

}
