package com.example.test02.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDto {
    private String roomId;
    private String roomName;

    public static ChatRoomDto create(String name) {
        ChatRoomDto room = new ChatRoomDto();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        return room;
    }

    //룸 아이디, 룸 네임.
    //룸 아이디를 임의로 부여해서 방을 생성.
    
}
