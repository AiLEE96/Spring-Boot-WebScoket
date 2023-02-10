package com.mysite.sbb.websocket;

import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import lombok.*;
import com.sun.istack.NotNull;
//lombok에 속해있는 @Data 어노테이션을 사용하게 되면 
//@NotNull 사용이 가능한걸로 알고 있었는데 안됨
//따라서 com.sun.istack.NotNull을 추가해줌.

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebChatRoomDto {

    @NotNull
    private String roomId; // 채팅방 아이디
    private String roomName; // 채팅방 이름 
    private int userCount; // 채팅방 인원수
    private int maxUserCnt; // 채팅방 최대 인원 제한
    private String roomPwd; // 채팅방 삭제시 필요한 pwd
    private boolean secretChk; // 채팅방 잠금 여부

    
    public enum ChatType{  // 문자 채팅, 화상 채팅 삭제
        MSG
    }
    
    private ChatType chatType; //  채팅 타입 여부
    // 화상 채팅과 문자 채팅 선택 비 활성화, 현재 문자 채팅만 가능.

    // ChatRoomDto 클래스는 하나로 가되 서비스를 나누었다.
    public ConcurrentMap<String, ?> userList = new ConcurrentHashMap<>();
    
}
