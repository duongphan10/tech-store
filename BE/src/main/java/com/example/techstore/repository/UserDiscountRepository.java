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
    @Query(value = "SELECT ud.* FROM user_discounts ud " +
            "INNER JOIN discount_codes ON ud.discount_id = discount_codes.id " +
            "WHERE " +
            "   ud.user_id = ?1 " +
            "   AND (?2 IS NULL OR discount_codes.type = ?2) " +
            "   AND (?3 IS NULL OR ud.status = ?3) ", nativeQuery = true)
    Page<UserDiscount> getAllByUserId(String userId,Boolean type,Boolean status,Pageable pageable);
    boolean existsByUserAndDiscountCode(User user, DiscountCode discountCode);
}
