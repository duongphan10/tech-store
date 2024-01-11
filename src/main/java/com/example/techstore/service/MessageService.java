package com.example.techstore.service;

import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.request.MessageRequestDto;
import com.example.techstore.domain.dto.response.MessageDto;

public interface MessageService {
    PaginationResponseDto<MessageDto> getAll(String roomId, String message, PaginationFullRequestDto paginationFullRequestDto);

    MessageDto create(MessageRequestDto messageRequestDto);
}