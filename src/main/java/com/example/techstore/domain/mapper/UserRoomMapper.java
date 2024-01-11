package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.UserRoomRequestDto;
import com.example.techstore.domain.dto.response.UserRoomDto;
import com.example.techstore.domain.entity.UserRoom;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserRoomMapper {
    UserRoom mapUserRoomCreateDtoToUserRoom(UserRoomRequestDto createDto);

    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "roomId", source = "room.id"),
    })
    UserRoomDto mapUserRoomToUserRoomDto(UserRoom userRoom);

    List<UserRoomDto> mapUserRoomToUserRoomDto(List<UserRoom> userRooms);

    void updateUserRoom(@MappingTarget UserRoom userRoom, UserRoomRequestDto updateDto);
}