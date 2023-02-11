package com.mysite.sbb.websocket;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.mysite.sbb.user.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebChatRoomController {
    
    // ChatService Bean 가져오기
    private final WebChatServiceMain chatServiceMain;

    // 채팅방 생성
    // 채팅방 생성 후 다시 / 로 return
    @PostMapping("/chat/createroom")
    public String createRoom(@RequestParam("roomName") String name,
                             @RequestParam("roomPwd") String roomPwd,
                             @RequestParam("secretChk") String secretChk,
                             @RequestParam(value = "maxUserCnt", defaultValue = "2") String maxUserCnt,
                             @RequestParam("chatType") String chatType,
                             //화상, 문자로 이뤄지는 채팅 타입 미 생성, 오로지 문자 형식의 채팅만
                             RedirectAttributes rttr) {

        log.info("check {}", secretChk);

        // 매개변수 : 방 이름, 패스워드, 방 잠금 여부, 방 인원수
        WebChatRoomDto room;

        room = chatServiceMain.createChatRoom(name, roomPwd, Boolean.parseBoolean(secretChk), Integer.parseInt(maxUserCnt));


        log.info("CREATE Chat Room [{}]", room);

        rttr.addFlashAttribute("roomName", room);
        return "redirect:/";
    }

    // 채팅방 입장 화면
    // 파라미터로 넘어오는 roomId 를 확인후 해당 roomId 를 기준으로
    // 채팅방을 찾아서 클라이언트를 chatroom 으로 보낸다.
    @GetMapping("/chat/room")
    public String roomDetail(Model model, String roomId, Principal principalDetails){
    //roomDetail 메소드 전체적인 수정필요
    //@AuthenticationPrincipal UserSecurityService principalDetails 해당 부분 잘못됨 꼭 수정
    //@AuthenticationPrincipal UserSecurityService -> Principal로 변경

        log.info("roomId {}", roomId);
        // principalDetails가 null이 아니라면 로그인 된 상태
        if (principalDetails != null) {
            // 세션에서 로그인 유저 정보를 가져옴
            model.addAttribute("user", principalDetails.getName());
        }

        WebChatRoomDto room = WebChatRoomMap.getInstance().getChatRooms().get(roomId);

        model.addAttribute("room", room);

        
        // 채팅의 타입이 화상, 문자가 아니라 오로지 문자
        // 따라서 채팅 룸을 구분해서 리턴해 줄 필요가 없다
        if (WebChatRoomDto.ChatType.MSG.equals(room.getChatType())) {
            return "chatroom";
        }

        return roomId;
        // 디폴트 리턴 값이 없어서 우선 생성한 리턴 값
        // 리턴 값 변경, Principal 객체를 리턴 할 때
        // 현재 로그인 정보를 반영 못할 수 있다.
        
        /*
        else{
            String uuid = UUID.randomUUID().toString().split("-")[0];
            model.addAttribute("uuid", uuid);
            model.addAttribute("roomId", room.getRoomId());
            model.addAttribute("roomName", room.getRoomName());
            //return "rtcroom";

            return "kurentoroom";
        }
        */
    
    }

    // 채팅방 비밀번호 확인
    @PostMapping("/chat/confirmPwd/{roomId}")
    @ResponseBody
    public boolean confirmPwd(@PathVariable String roomId, @RequestParam String roomPwd){

        // 넘어온 roomId 와 roomPwd 를 이용해서 비밀번호 찾기
        // 찾아서 입력받은 roomPwd 와 room pwd 와 비교해서 맞으면 true, 아니면  false
        return chatServiceMain.confirmPwd(roomId, roomPwd);
    }

    // 채팅방 삭제
    @GetMapping("/chat/delRoom/{roomId}")
    public String delChatRoom(@PathVariable String roomId){

        // roomId 기준으로 chatRoomMap 에서 삭제, 해당 채팅룸 안에 있는 사진 삭제
        chatServiceMain.delChatRoom(roomId);

        return "redirect:/";
    }

    // 유저 카운트
    @GetMapping("/chat/chkUserCnt/{roomId}")
    @ResponseBody
    public boolean chUserCnt(@PathVariable String roomId){

        return chatServiceMain.chkRoomUserCnt(roomId);
    }
}
