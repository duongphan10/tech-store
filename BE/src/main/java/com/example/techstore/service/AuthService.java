package com.example.techstore.service;

import com.example.techstore.domain.dto.request.LoginRequestDto;
import com.example.techstore.domain.dto.request.TokenRefreshRequestDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.LoginResponseDto;
import com.example.techstore.domain.dto.response.TokenRefreshResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto request);

    TokenRefreshResponseDto refresh(TokenRefreshRequestDto request);

    CommonResponseDto logout(HttpServletRequest request);

}
