package com.mysite.sbb.websocket;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mysite.sbb.user.SiteUser;



@Mapper(componentModel = "spring")
public interface ChatUserMapper {
    ChatUserMapper INSTANCE = Mappers.getMapper(ChatUserMapper.class);

    ChatUserDto toDto(SiteUser e);
    SiteUser toEntity(ChatUserDto d);
}
