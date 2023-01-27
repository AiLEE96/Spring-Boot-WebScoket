package com.sample.stomp.entity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sample.stomp.entity.Member;

@Repository
public interface repository extends JpaRepository<Member, Long> {
    
    Optional<Member> findById(String id); 
    
}