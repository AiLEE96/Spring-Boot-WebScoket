package com.websocket.chat.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;
import com.websocket.chat.dto.*;

@Repository
public class WebScChatRoomRepository {

    private Map<String, WebScChatRoomDto> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<WebScChatRoomDto> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public WebScChatRoomDto findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public WebScChatRoomDto createChatRoom(String name) {
        WebScChatRoomDto chatRoom = WebScChatRoomDto.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}