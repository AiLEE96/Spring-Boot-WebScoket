package com.chat.stomp.controller;

import com.chat.stomp.dto.WebSocketMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class WebSocketChatController {
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(WebSocketMessageDto message) {
        if (WebSocketMessageDto.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
    // 웹소켓 핸들러의 역할을 대체, 클라이언트에서 prefix를 붙여서 /pub/chat/message로 발행을 요청
    // WebSocketChatController가 이를 처리, 메시지가 발행이된다.
    // 이때 발행된 메시지는 getRoomId를 통해서 확인된 채팅방에 전송이 된다.
    // 클라이언트가 존재하는 /sub/chat/room/roomId의 특정 채팅방에 전송된 메시지가 출력된다.
}
