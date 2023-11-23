package com.example.techstore.repository;

import com.example.techstore.domain.entity.DiscountCode;
import com.example.techstore.domain.entity.User;
import com.example.techstore.domain.entity.UserDiscount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDiscountRepository extends JpaRepository<UserDiscount,String> {
    @Query(value = "SELECT user_discounts.* FROM user_discounts", nativeQuery = true)
    Page<UserDiscount> getAll(Pageable pageable);
    boolean existsByUserAndDiscountCode(User user, DiscountCode discountCode);
}
