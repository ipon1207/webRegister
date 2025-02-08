package com.example.webregister.repository;

import com.example.webregister.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

    List<SaleDetail> findBySaleIdOrderBySaleDetailIdAsc(Long saleId);
    @Transactional
    void deleteBySaleId(Long saleId);

}