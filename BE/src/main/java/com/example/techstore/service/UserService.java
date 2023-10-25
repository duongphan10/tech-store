package com.example.techstore.service;

import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.response.UserDto;
import com.example.techstore.security.UserPrincipal;

public interface UserService {

    UserDto getUserById(String userId);

    PaginationResponseDto<UserDto> getCustomers(PaginationFullRequestDto request);

    UserDto getCurrentUser(UserPrincipal principal);

}
