package com.example.techstore.repository;

import com.example.techstore.domain.entity.DiscountCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode,String> {
    @Query(value = "SELECT discount_codes.* FROM discount_codes", nativeQuery = true)
    Page<DiscountCode> getAll(Pageable pageable);
    Optional<DiscountCode> findByCode(String code);

}
