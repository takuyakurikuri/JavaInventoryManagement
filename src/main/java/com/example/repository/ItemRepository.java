package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i WHERE " +
            "(:keyword IS NULL OR i.name LIKE %:keyword% OR i.janCode LIKE %:keyword%) AND " +
            "(:categoryId IS NULL OR i.category.id = :categoryId) AND " +
            "(:supplierId IS NULL OR i.supplier.id = :supplierId)")
     List<Item> findByConditions(@Param("keyword") String keyword,
                                 @Param("categoryId") Long categoryId,
                                 @Param("supplierId") Long supplierId);


}
