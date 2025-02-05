package com.example.webregister.repository;

import com.example.webregister.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIsDeletedFalseOrderByProductIdAsc();
    List<Product> findByUserIdAndIsDeletedFalseOrderByProductIdAsc(Long userId);
    Product findByProductId(Long productId);
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.isDeleted = true WHERE p.productId = :productId")
    void logicallyDeleteByProductId(Long productId);

}