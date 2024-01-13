package com.example.techstore.controller;

import com.example.techstore.base.RestApiV1;
import com.example.techstore.base.VsResponseUtil;
import com.example.techstore.constant.UrlConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.security.CurrentUser;
import com.example.techstore.security.UserPrincipal;
import com.example.techstore.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class RoomController {
    private final RoomService roomService;

    @Tag(name = "room-controller")
    @Operation(summary = "API get room by id")
    @GetMapping(UrlConstant.Room.GET_BY_ID)
    public ResponseEntity<?> getRoomById(@PathVariable String id) {
        return VsResponseUtil.success(roomService.getById(id));
    }

    @Tag(name = "room-controller")
    @Operation(summary = "API get all room by Page")
    @GetMapping(UrlConstant.Room.GET_ALL)
    public ResponseEntity<?> getAllRoom(@Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(roomService.getAll(paginationFullRequestDto));
    }

    @Tag(name = "room-controller")
    @Operation(summary = "API create room")
    @PostMapping(UrlConstant.Room.CREATE)
    public ResponseEntity<?> createRoom(@Parameter(name = "principal", hidden = true) @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(roomService.create(user.getId()));
    }
}
