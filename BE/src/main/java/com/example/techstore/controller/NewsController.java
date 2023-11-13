package com.example.techstore.controller;

import com.example.techstore.base.RestApiV1;
import com.example.techstore.base.VsResponseUtil;
import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.UrlConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.request.NewsRequestDto;
import com.example.techstore.domain.entity.News;
import com.example.techstore.domain.mapper.NewsMapper;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.NewsRepository;
import com.example.techstore.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestApiV1
public class NewsController {
    private final NewsService newsService;
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Tag(name = "news-controller")
    @Operation(summary = "API get all news")
    @GetMapping(UrlConstant.News.GET_ALL)
    public ResponseEntity<?> getAllNews() {
        return VsResponseUtil.success(newsService.getAll());
    }

    @Tag(name = "news-controller")
    @Operation(summary = "API get news by id")
    @GetMapping(UrlConstant.News.GET_BY_ID)
    public ResponseEntity<?> getNewsById(@PathVariable String id) {
        return VsResponseUtil.success(newsService.getById(id));
    }

    @Tag(name = "news-controller")
    @Operation(summary = "API get news by status")
    @GetMapping(UrlConstant.News.GET_BY_STATUS)
    public ResponseEntity<?> getNewsByStatus(@RequestParam(required = false, defaultValue = "true") Boolean status) {
        List<News> newsList = newsRepository.getByStatus(status);
        if(newsList.size() == 0){
            throw new NotFoundException(ErrorMessage.News.ERR_NOT_FOUND_STATUS,new String[]{status.toString()});
        }
        return VsResponseUtil.success(newsMapper.mapNewsToNewsDto(newsList));
    }

    @Tag(name = "news-controller")
    @Operation(summary = "API create news")
    @PostMapping(UrlConstant.News.CREATE)
    public ResponseEntity<?> createNews(@Valid @ModelAttribute NewsRequestDto createDto) {
        return VsResponseUtil.success(newsService.create(createDto));
    }

    @Tag(name = "news-controller")
    @Operation(summary = "API update news")
    @PatchMapping(UrlConstant.News.UPDATE)
    public ResponseEntity<?> updateNews(@PathVariable String id,@Valid @ModelAttribute NewsRequestDto updateDto) {
        return VsResponseUtil.success(newsService.update(id, updateDto));
    }

    @Tag(name = "news-controller")
    @Operation(summary = "API delete news")
    @DeleteMapping(UrlConstant.News.DELETE)
    public ResponseEntity<?> deleteNews(@PathVariable String id) {
        return VsResponseUtil.success(newsService.deleteById(id));
    }

    @Tag(name = "news-controller")
    @Operation(summary = "API get News by Page")
    @GetMapping(UrlConstant.News.GET_BY_PAGE)
    public ResponseEntity<?> getAllNewsByPage(@Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(newsService.getAll(paginationFullRequestDto));
    }
}
