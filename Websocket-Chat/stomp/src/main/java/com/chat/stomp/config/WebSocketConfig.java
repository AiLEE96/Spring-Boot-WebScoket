package com.chat.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub"); // 메시지 발행
        config.setApplicationDestinationPrefixes("/pub"); // 메시지 수신
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp").setAllowedOriginPatterns("*")
                .withSockJS();
                // setAllowedOrigins("*") 사용할경우 아래 에러 발생.
                // setAllowedOriginPatterns("*") 사용시 문제 해결.

                /*java.lang.IllegalArgumentException: When allowCredentials is true,
                  allowedOrigins cannot contain the special value "*" since 
                  that cannot be set on the "Access-Control-Allow-Origin" response header.
                  To allow credentials to a set of origins, list them explicitly or consider
                  using "allowedOriginPatterns" instead. */

                // 웹소켓 연결 endpoint == ws://localhost:8080/stomp
    }
}
