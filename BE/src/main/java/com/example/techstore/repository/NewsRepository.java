package com.example.techstore.repository;

import com.example.techstore.domain.dto.response.NewsDto;
import com.example.techstore.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,String> {
    @Query(value = "SELECT * FROM news WHERE status = ?1", nativeQuery = true)
    List<News> getByStatus(Boolean status);

}
