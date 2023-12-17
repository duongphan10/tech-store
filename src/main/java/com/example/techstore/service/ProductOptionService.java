package com.example.techstore.service;

import com.example.techstore.domain.dto.request.ProductOptionCreateDto;
import com.example.techstore.domain.dto.request.ProductOptionUpdateDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.ProductOptionDto;

import java.util.List;

public interface ProductOptionService {
    ProductOptionDto getById(String id);

    List<ProductOptionDto> getAllByProductId(String productId);

    ProductOptionDto create(ProductOptionCreateDto productOptionCreateDto);

    ProductOptionDto updateById(String id, ProductOptionUpdateDto productOptionUpdateDto);

    CommonResponseDto deleteById(String id);
}
