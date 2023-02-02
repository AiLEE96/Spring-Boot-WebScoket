package com.mysite.sbb.websocket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.mysite.sbb.user.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;
    private String roomId; //채팅방 ID
    private String sender; //보내는 사람, 회원가입한 사람의 닉네임.
    private String message; //내용

    //여러개의 채팅방이 하나의 아이디로 개설될 수 있지만
    //고유한 속성을 추가해서 채팅방을 누가 개설했는지 알 필요는 없다.
    //따라서 ManyToOne 관계가 성립하지만 따로 @ManyToOne을 추가하지 않는다.
    

}