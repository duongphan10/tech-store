package com.example.techstore.service;

import com.example.techstore.domain.dto.request.CategoryRequestDto;
import com.example.techstore.domain.dto.request.NewsRequestDto;
import com.example.techstore.domain.dto.response.CategoryDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.NewsDto;

import java.util.List;

public interface NewsService {
    NewsDto getById(String id);
    List<NewsDto> getAll();
    //List<NewsDto> getByStatus(Boolean status);
    NewsDto create(NewsRequestDto createDto);
    NewsDto update(String id,NewsRequestDto updateDto);
    CommonResponseDto deleteById(String id);
}
