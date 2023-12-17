package com.example.techstore.domain.mapper;

import com.example.techstore.constant.CommonConstant;
import com.example.techstore.domain.dto.request.UserCreateDto;
import com.example.techstore.domain.dto.request.UserUpdateDto;
import com.example.techstore.domain.dto.response.UserDto;
import com.example.techstore.domain.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User mapUserCreateDtoToUser(UserCreateDto userCreateDTO);

    @Mappings({
            @Mapping(target = "roleName", source = "role.name")
    })
    UserDto mapUserToUserDto(User user);

    List<UserDto> mapUsersToUserDtos(List<User> user);

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "fullName", source = "fullName"),
            @Mapping(target = "gender", source = "gender"),
            @Mapping(target = "birthday", source = "birthday", dateFormat = CommonConstant.PATTERN_DATE),
            @Mapping(target = "phone", source = "phone"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "avatar", source = "avatar", ignore = true)
    })
    void updateUser(@MappingTarget User user, UserUpdateDto userUpdateDto);
}
