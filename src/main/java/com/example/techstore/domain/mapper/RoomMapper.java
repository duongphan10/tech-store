package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.RoomRequestDto;
import com.example.techstore.domain.dto.response.RoomDto;
import com.example.techstore.domain.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {
    Room mapRoomCreateDtoToRoom(RoomRequestDto createDto);

    RoomDto mapRoomToRoomDto(Room room);

    List<RoomDto> mapRoomToRoomDto(List<Room> rooms);

    void updateRoom(@MappingTarget Room room, RoomRequestDto updateDto);
}