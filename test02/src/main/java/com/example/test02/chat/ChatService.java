package com.example.test02.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    
    private Map<String, RoomDto> chatRooms;

    @PostConstruct //의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<RoomDto> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        List<RoomDto> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);

        return result;
    }

    //채팅방 하나 불러오기
    public RoomDto findById(String roomId) {
        return chatRooms.get(roomId);
    }

    //채팅방 생성
    public RoomDto createRoom(String name) {
        RoomDto chatRoom = RoomDto.create(name);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
