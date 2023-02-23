package com.websocket.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import com.websocket.chat.dto.WebScChatMessageDto;

@RequiredArgsConstructor
@Controller
public class WebScChatController {
    //메시지 송신 구현, @MessageMapping을 통해서 WebSocket 전송되는 메시지를 송신한다.
    //클라이언트가 메시지를 입력해서 전송하게 되면
    //"/pub/chat/message"로 요청이 전송, Controller가 해당 메시지를 처리하게 된다.
    //이후 convertAndSend("/sub/chat/room/" + message.getRoomId(), message)를 통해서
    //"/sub/chat/room/" + message.getRoomId() = {roomId}, message = 송신 메시지"
    //즉, 메시지가 입력된 주소를 구독하고 있다가 "/sub/chat/room/{roomId}" + 송신 메시지"
    //구독된 주소로 메시지 송신이 이뤄지게 된다.

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(WebScChatMessageDto message) {

        if (WebScChatMessageDto.MessageType.ENTER.equals(message.getType())) {
            //WebScChatMessageDto.MessageType.JOIN.equals(message.getType())
            //WebScChatMessageDto.MessageType.ENTER.equals(message.getType())
            //JOIN -> ENTER로 수정, 메시지 타입의 객체는 ENTER, TALK로 이뤄져 있기 때문.
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }

        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
