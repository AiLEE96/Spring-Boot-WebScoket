package com.websocket.chat.dto;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
public class WebScChatRoomDto {
    private String roomId;
    private String name;

    public static WebScChatRoomDto create(String name) {
        WebScChatRoomDto chatRoom = new WebScChatRoomDto();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
        //채팅방 생성시 채팅방 이름과 식별을 위한 번호 부여
    }
}