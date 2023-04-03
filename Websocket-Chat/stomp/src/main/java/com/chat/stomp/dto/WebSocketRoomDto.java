package com.chat.stomp.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class WebSocketRoomDto {
    private String roomId;
    private String roomName;

    public static WebSocketRoomDto create(String name) {
        WebSocketRoomDto WebSocketRoomDto = new WebSocketRoomDto();
        WebSocketRoomDto.roomId = UUID.randomUUID().toString();
        WebSocketRoomDto.roomName = name;
        return WebSocketRoomDto;
    }
    // 기존 단일 채팅방과는 달리 STOMP는 Pub/Sub을 통해서 채팅방에 대한 구독관리가 이뤄진다.
    // 따라서 채팅을 위한 웹 소켓 세션관리 및 해당 채팅방에서 이뤄지는 메시지 송, 수신 구현이 필요없어진다.
}
