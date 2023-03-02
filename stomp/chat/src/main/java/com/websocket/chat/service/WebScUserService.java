package com.websocket.chat.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.websocket.chat.repository.WebScUserRepository;
import com.websocket.chat.dto.WebScUserDto;

@RequiredArgsConstructor
@Service
public class WebScUserService {
    private final WebScUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public WebScUserDto create(String username, String nickname, String password) {
        WebScUserDto user = new WebScUserDto();
        
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }
}
