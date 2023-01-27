package com.sample.stomp.controller;

import com.sample.stomp.model.ChatRoom;
import com.sample.stomp.service.ChatService;

import jakarta.servlet.http.HttpSession;

import com.sample.stomp.entity.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;

    @Autowired
	ChatService memberService;


    //로그인 입력 화면
    @GetMapping("/index")
    public String index(Member member) {
        return "/chat/inedx";
    }

    @PostMapping("/join")
	public String loginPost(HttpSession session, Member member) {
		Member memberNow = memberService.findById(member.getId()).get();

		// 로그인 성공
		if (memberNow.getPw().equals(member.getPw())) {
			session.setAttribute("memberId", member.getId());
			System.out.println("로그인에 성공했습니다.");
			return "redirect:/chat";
		}

		// 로그인 실패
		else {
			System.out.println("패스워드가 일치하지 않습니다.");
			return "redirect:/";
		}

	}
    //회원 가입 및 조회
	@GetMapping("/join")
	public String joinGet(Member member) {
		return "/chat/join";
	}

	@PostMapping("/index")
	public String joinPost(Member member) {

		memberService.save(member);
		System.out.println(member + "가 가입되었습니다.");

		return "redirect:/chat/index";
	}

	@GetMapping("/roomdetail")
	public String chatGET(HttpSession session) {

		System.out.println("@ChatController, chat GET()");

		return "/chat/roomdetail";
	}

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }
    /* 
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }
    */
}