package com.mysite.sbb.websocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class WebChatServiceMain {

    private final WebChatMsgService msgChatService;
    //private final RtcChatService rtcChatService;
    //화상회의 기능 사용 안함

    //채팅방 삭제에 따른 채팅방의 사진 삭제를 위한 fileService 선언
    //private final FileService fileService;
    //파일 서비스 기능 추가예정, 현재 사용 안함


    // 전체 채팅방 조회
    public List<WebChatRoomDto> findAllRoom(){
        // 채팅방 생성 순서를 최근순으로 반환
        List<WebChatRoomDto> chatRooms = new ArrayList<>(WebChatRoomMap.getInstance().getChatRooms().values());
        Collections.reverse(chatRooms);

        return chatRooms;
    }

    // roomID 기준으로 채팅방 찾기
    public WebChatRoomDto findRoomById(String roomId){
        return WebChatRoomMap.getInstance().getChatRooms().get(roomId);
    }

    // roomName 로 채팅방 만들기
    public WebChatRoomDto createChatRoom(String roomName, String roomPwd, boolean secretChk, int maxUserCnt){
        WebChatRoomDto room;
        room = msgChatService.createChatRoom(roomName, roomPwd, secretChk, maxUserCnt);
        
        /*
        // 채팅방 타입에 따라서 사용되는 Service 구분
        if(chatType.equals("msgChat")){
            room = msgChatService.createChatRoom(roomName, roomPwd, secretChk, maxUserCnt);
        }else{
            room = rtcChatService.createChatRoom(roomName, roomPwd, secretChk, maxUserCnt);
        }
        // 채팅방 타입이 따로 존재하지 않기 때문에 서비스 미 구분
        */

        return room;
    }

    // 채팅방 비밀번호 조회
    public boolean confirmPwd(String roomId, String roomPwd) {
        //String pwd = WebChatRoomMap.get(roomId).getRoomPwd();
        return roomPwd.equals(WebChatRoomMap.getInstance().getChatRooms().get(roomId).getRoomPwd());

    }

    // 채팅방 인원+1
    public void plusUserCnt(String roomId){
        log.info("cnt {}",WebChatRoomMap.getInstance().getChatRooms().get(roomId).getUserCount());
        WebChatRoomDto room = WebChatRoomMap.getInstance().getChatRooms().get(roomId);
        room.setUserCount(room.getUserCount()+1);
    }

    // 채팅방 인원-1
    public void minusUserCnt(String roomId){
        WebChatRoomDto room = WebChatRoomMap.getInstance().getChatRooms().get(roomId);
        room.setUserCount(room.getUserCount()-1);
    }

    // maxUserCnt 에 따른 채팅방 입장 여부
    public boolean chkRoomUserCnt(String roomId){
        WebChatRoomDto room = WebChatRoomMap.getInstance().getChatRooms().get(roomId);

        if (room.getUserCount() + 1 > room.getMaxUserCnt()) {
            return false;
        }
        return true;
    }

    //채팅방 삭제, 채팅방 삭제 기능 현재 미구현. 만약 된다면 코드 재확인
    public void delChatRoom(String roomId){

        try {
            // 채팅방 타입에 따라서 단순히 채팅방만 삭제할지 업로드된 파일도 삭제할지 결정
            WebChatRoomMap.getInstance().getChatRooms().remove(roomId);
            
            /*
            if (WebChatRoomMap.getInstance().getChatRooms().get(roomId).equals(WebChatRoomDto.ChatType.MSG)) { // MSG 채팅방은 사진도 추가 삭제
                //채팅방 안에 있는 파일 삭제
                fileService.deleteFileDir(roomId);
            }
            */

            log.info("삭제 완료 roomId : {}", roomId);

        } catch (Exception e) { // 만약에 예외 발생시 확인하기 위해서 try catch
            System.out.println(e.getMessage());
        }

    }

}