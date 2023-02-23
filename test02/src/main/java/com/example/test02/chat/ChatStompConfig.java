package com.example.test02.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class ChatStompConfig implements WebSocketMessageBrokerConfigurer {

    private final WebChatStompHandler chatStompHandler;
    //JWT 토큰을 인증을 위한 핸들러

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS();
        //웹 소켓 통신을 하기위한 엔드포인트 설정
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/queue", "/topic");

        registry.setApplicationDestinationPrefixes("/app");
        //메시지를 주고 받는 목적지 설정과 통신시에 /app 아래로 통신이 이뤄진다.
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(chatStompHandler);
        //토큰 검증을 위한 인터셉터 설정으로
        //위에서 선언한 chatStompHandler를 인터셉터로 지정했다.
    }
}
