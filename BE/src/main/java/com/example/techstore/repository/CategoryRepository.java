package com.example.techstore.repository;

import com.example.techstore.domain.entity.Category;
import com.example.techstore.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    //boolean existsByCategoryName(String name);
    @Query(value = "SELECT * FROM categories WHERE name = ?1", nativeQuery = true)
    Category findByCategoryName(String name);
    @Query(value = "SELECT created_by FROM categories WHERE id = ?1", nativeQuery = true)
    String findUserCreate(String id);

}
