package com.example.techstore.repository;

import com.example.techstore.domain.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, String> {
    @Query(value = "SELECT * FROM product_options WHERE product_id = ?1", nativeQuery = true)
    List<ProductOption> getAllByProductId(String productId);
}
