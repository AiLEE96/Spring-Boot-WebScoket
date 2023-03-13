package com.websocket.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.websocket.chat.dto.WebScUserDto;

public interface WebScUserRepository extends JpaRepository<WebScUserDto, Long> {
}
