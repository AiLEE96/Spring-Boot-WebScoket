package com.mysite.sbb.websocket;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebChatMainController {
    private final WebChatServiceMain chatServiceMain;

    // 채팅 리스트 화면
    // / 로 요청이 들어오면 전체 채팅룸 리스트를 담아서 return

    // 스프링 시큐리티의 로그인 유저 정보는 Security 세션의 PrincipalDetails 안에 담긴다
    // 정확히는 PrincipalDetails 안에 ChatUser 객체가 담기고, 이것을 가져오면 된다.
    @GetMapping("/session")
    public String goChatRoom(Model model, Principal principalDetails){
        // @AuthenticationPrincipal PrincipalDetails principalDetails
        // Principal principalDetails 수정

        model.addAttribute("list", chatServiceMain.findAllRoom());

        // principalDetails 가 null 이 아니라면 로그인 된 상태!!
        if (principalDetails != null) {
            // 세션에서 로그인 유저 정보를 가져옴
            model.addAttribute("user", principalDetails.getName());
            // principalDetails.getUser()
            // principalDetails.getName()으로 수정.
            log.debug("user [{}] ",principalDetails);
        }

        //model.addAttribute("user", "hey");
        log.debug("SHOW ALL ChatList {}", chatServiceMain.findAllRoom());
        return "roomlist";
    }

    
}
