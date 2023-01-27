package com.sample.stomp.entity;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sample.stomp.entity.Member;
import com.sample.stomp.entity.repository;
@Service
public class MemberService {
    @Autowired
    repository memberRepository;
    
    public void save(Member member) {
        memberRepository.save(member);
    }
    
    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }
}