package com.example.techstore.repository;

import com.example.techstore.domain.entity.News;
import com.example.techstore.domain.entity.Slide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideRepository extends JpaRepository<Slide,String> {
    @Query(value = "SELECT slides.* FROM slides", nativeQuery = true)
    Page<Slide> getAll(Pageable pageable);
    @Query(value = "SELECT * FROM slides WHERE status = ?1", nativeQuery = true)
    Page<Slide> getByStatus(Boolean status,Pageable pageable);
    @Query(value = "SELECT * FROM slides WHERE position = ?1", nativeQuery = true)
    Slide findByPosition(Integer position);
    @Query(value = "SELECT * FROM slides WHERE product_id = ?1", nativeQuery = true)
    Slide findByProductId(String productId);
}
