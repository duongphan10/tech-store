package com.example.techstore.service.impl;

import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.MessageConstant;
import com.example.techstore.domain.dto.request.LoginRequestDto;
import com.example.techstore.domain.dto.request.TokenRefreshRequestDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.LoginResponseDto;
import com.example.techstore.domain.dto.response.TokenRefreshResponseDto;
import com.example.techstore.domain.entity.User;
import com.example.techstore.exception.UnauthorizedException;
import com.example.techstore.repository.UserRepository;
import com.example.techstore.security.UserPrincipal;
import com.example.techstore.security.jwt.JwtTokenProvider;
import com.example.techstore.service.AuthService;
import com.example.techstore.util.CheckLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        try {
            Authentication authentication = null;
            if (CheckLoginRequest.isPhone(request.getAccount())) {
                User user = userRepository.findUserByPhone(request.getAccount()).orElseThrow(
                        () -> new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_USERNAME));
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
            } else {
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getAccount(), request.getPassword()));
            }
            if (authentication == null) {
                throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_USERNAME);
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String accessToken = jwtTokenProvider.generateToken(userPrincipal, Boolean.FALSE);
            String refreshToken = jwtTokenProvider.generateToken(userPrincipal, Boolean.TRUE);
            return new LoginResponseDto(accessToken, refreshToken, userPrincipal.getId(), authentication.getAuthorities());
        } catch (InternalAuthenticationServiceException e) {
            throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_USERNAME);
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_PASSWORD);
        }
    }

    @Override
    public TokenRefreshResponseDto refresh(TokenRefreshRequestDto request) {
        return null;
    }

    @Override
    public CommonResponseDto logout(HttpServletRequest request,
                                    HttpServletResponse response, Authentication authentication) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return new CommonResponseDto(true, MessageConstant.SUCCESSFULLY_LOGOUT);
    }

}
