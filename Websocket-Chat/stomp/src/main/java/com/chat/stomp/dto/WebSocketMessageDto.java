package com.chat.stomp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebSocketMessageDto {
        public enum MessageType {
            ENTER, TALK
        } // 메시지 타입 : 입장, 채팅
    
        private MessageType type; // 메시지 타입
        private String roomId; // 방번호
        private String sender; // 메시지 보낸사람
        private String message; // 메시지
    
}
