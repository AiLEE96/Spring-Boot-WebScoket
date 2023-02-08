package com.mysite.sbb.websocket;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import lombok.*;

@Getter
@Setter
public class WebChatRoomMap {
    private static WebChatRoomMap webChatRoomMap = new WebChatRoomMap();
    private ConcurrentMap<String, WebChatRoomDto> chatRooms = new ConcurrentHashMap<>();

    private WebChatRoomMap(){}

    public static WebChatRoomMap getInstance(){
        return webChatRoomMap;
    }
}
