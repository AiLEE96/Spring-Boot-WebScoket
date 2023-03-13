package com.websocket.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebScSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll()
                // new AntPathRequestMatcher("/chat/**")).permitAll() - GET요청은 정상적으로 도달
                // 그러나 페이지를 구성하는 CSS 및 스크립트가 불러와지지 않는다
                ;
        return http.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

// WebSecurityConfigurerAdapter deprecated
// 스프링에서 다른 방식으로 시큐리티 설정을 권장
// 기존엔 상속을 받아 설정을 오버라이딩 하는 방식
// 변경된 방식에서는 상속받지 않고 모두 Bean으로 등록해서 사용
