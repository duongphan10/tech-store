package com.example.techstore.controller;

import com.example.techstore.base.RestApiV1;
import com.example.techstore.base.VsResponseUtil;
import com.example.techstore.constant.UrlConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.request.UserCreateDto;
import com.example.techstore.domain.dto.request.UserUpdateDto;
import com.example.techstore.security.CurrentUser;
import com.example.techstore.security.UserPrincipal;
import com.example.techstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class UserController {

    private final UserService userService;

    @Tags({@Tag(name = "user-controller-admin"), @Tag(name = "user-controller")})
    @Operation(summary = "API get user")
    @GetMapping(UrlConstant.User.GET_BY_ID)
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        return VsResponseUtil.success(userService.getUserDtoById(userId));
    }

    @Tags({@Tag(name = "user-controller-admin"), @Tag(name = "user-controller")})
    @Operation(summary = "API get current user login")
    @GetMapping(UrlConstant.User.GET_CURRENT_USER)
    public ResponseEntity<?> getCurrentUser(@Parameter(name = "principal", hidden = true)
                                            @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(userService.getCurrentUser(user));
    }

    @Tag(name = "user-controller-admin")
    @Operation(summary = "API get all user")
    @GetMapping(UrlConstant.User.GET_ALL)
    public ResponseEntity<?> getAllUser(@Valid @ParameterObject PaginationFullRequestDto requestDTO) {
        return VsResponseUtil.success(userService.getAll(requestDTO));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API create user")
    @PostMapping(UrlConstant.User.CREATE)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return VsResponseUtil.success(userService.create(userCreateDto));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API update user")
    @PatchMapping(UrlConstant.User.UPDATE)
    public ResponseEntity<?> updateUser(@Parameter(name = "principal", hidden = true)
                                        @CurrentUser UserPrincipal user,
                                        @Valid @ModelAttribute UserUpdateDto userUpdateDto) {
        return VsResponseUtil.success(userService.update(user.getId(), userUpdateDto));
    }

}
