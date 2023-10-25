package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.UserCreateDto;
import com.example.techstore.domain.dto.response.UserDto;
import com.example.techstore.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreateDto userCreateDTO);

    @Mappings({
            @Mapping(target = "roleName", source = "user.role.name"),
    })
    UserDto toUserDto(User user);

    List<UserDto> toUserDtos(List<User> user);

}
