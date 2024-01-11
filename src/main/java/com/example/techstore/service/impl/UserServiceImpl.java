package com.example.techstore.service.impl;

import com.example.techstore.constant.CommonConstant;
import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.RoleConstant;
import com.example.techstore.constant.SortByDataConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.pagination.PagingMeta;
import com.example.techstore.domain.dto.request.UserCreateDto;
import com.example.techstore.domain.dto.request.UserUpdateDto;
import com.example.techstore.domain.dto.response.UserDto;
import com.example.techstore.domain.entity.User;
import com.example.techstore.domain.mapper.UserMapper;
import com.example.techstore.exception.AlreadyExistException;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.RoleRepository;
import com.example.techstore.repository.UserRepository;
import com.example.techstore.security.UserPrincipal;
import com.example.techstore.service.RoomService;
import com.example.techstore.service.UserService;
import com.example.techstore.util.PaginationUtil;
import com.example.techstore.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UploadFileUtil uploadFileUtil;
    private final RoomService roomService;

    public User getById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
    }

    @Override
    public UserDto getUserDtoById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public PaginationResponseDto<UserDto> getAll(PaginationFullRequestDto paginationFullRequestDto) {
        //Pagination
        //paginationFullRequestDto.setIsAscending(true);
        int pageSize = paginationFullRequestDto.getPageSize() != CommonConstant.PAGE_SIZE_DEFAULT
                ? paginationFullRequestDto.getPageSize() : CommonConstant.NUM_OF_USER_PER_PAGE;
        paginationFullRequestDto.setPageSize(pageSize);
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.USER);

        //Create Output
        Page<User> userPage = userRepository.findAll(pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.USER, userPage);

        List<UserDto> userDtos =
                userMapper.mapUsersToUserDtos(userPage.getContent());
        return new PaginationResponseDto<>(meta, userDtos);
    }

    @Override
    public UserDto getCurrentUser(UserPrincipal user) {
        return this.getUserDtoById(user.getId());
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        if (userRepository.existsByPhone(userCreateDto.getPhone())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_PHONE, new String[]{userCreateDto.getPhone()});
        }
        if (userRepository.existsByUsername(userCreateDto.getUsername())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_USERNAME, new String[]{userCreateDto.getUsername()});
        }
        User user = userMapper.mapUserCreateDtoToUser(userCreateDto);
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setRole(roleRepository.findByName(RoleConstant.USER));
        userRepository.save(user);
        roomService.create(user.getId());
        return userMapper.mapUserToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto update(String userId, UserUpdateDto userUpdateDto) {
        User user = this.getById(userId);

        if (userRepository.existsByPhone(userUpdateDto.getPhone())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_PHONE, new String[]{userUpdateDto.getPhone()});
        }
        if (userRepository.existsByUsername(userUpdateDto.getUsername())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_USERNAME, new String[]{userUpdateDto.getUsername()});
        }

        userMapper.updateUser(user, userUpdateDto);
        if (userUpdateDto.getAvatar() != null) {
            if (user.getAvatar() != null) {
                uploadFileUtil.destroyImageWithUrl(user.getAvatar());
            }
            user.setAvatar(uploadFileUtil.uploadImage(userUpdateDto.getAvatar()));
        }
        return userMapper.mapUserToUserDto(userRepository.save(user));
    }
}
