package com.annuaire.loginservice.mappers;

import com.annuaire.loginservice.dto.UserDto;
import com.annuaire.loginservice.model.AccountUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.identifiant", target = "identifiant")
    @Mapping(source = "token", target = "token")
    UserDto toUserDto(AccountUser user, String token);
}
