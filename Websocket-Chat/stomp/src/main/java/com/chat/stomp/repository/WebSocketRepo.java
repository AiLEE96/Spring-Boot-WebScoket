package com.chat.stomp.repository;

import com.chat.stomp.dto.WebSocketRoomDto;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class WebSocketRepo {
    private Map<String, WebSocketRoomDto> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<WebSocketRoomDto> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public WebSocketRoomDto findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public WebSocketRoomDto createChatRoom(String name) {
        WebSocketRoomDto chatRoom = WebSocketRoomDto.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
    // 현재 채팅방 정보를 Map으로 관리, 그러나 서비스에선 DB 또는 다른 저장 매체에 채팅방 정보를 저장하도록 구현
    // 채팅방을 사용하기 위해선 간단한 회원가입과 회원가입시 입력한 닉네임으로 채팅이 진행될 예정이기 때문에
    // Map -> DB로 변경이 필요하다.
}
