package com.example.webregister.repository;

import com.example.webregister.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByUserIdOrderBySaleIdAsc(Long userId);
    @Transactional
    void deleteBySaleId(Long saleId);

}