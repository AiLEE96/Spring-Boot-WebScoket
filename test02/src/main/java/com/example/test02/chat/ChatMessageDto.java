package com.example.test02.chat;

//@Builder 어노테이션 없이 진행, 추후 해당 어노테이션 없이 진행했을 때 성공 할 경우 @Builder 추가.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    
    public enum MessageType {
        ENTER, TALK
    }

    //LEAVE 삭제, 나중에 추가하기.

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

    //메시지 타입, 룸 아이디, 발신자, 메시지.
    //메시지 타입 = 입장, 떠남, 메시지.
    //메시지 타입에 따라서 동작 방식이 달라진다.
    //타입에 따른 동작 방식에 대한 해설은 공부 후 주석 작성.

}
