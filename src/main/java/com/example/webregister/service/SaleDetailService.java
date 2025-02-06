package com.example.webregister.service;

import com.example.webregister.model.SaleDetail;
import com.example.webregister.repository.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleDetailService {

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    public void insertSaleDetail(Long saleId, Long productId, String productName, Integer productValue, Integer quantity, Integer amount) {

        SaleDetail saveSaleDetail = new SaleDetail();
        saveSaleDetail.setSaleId(saleId);
        saveSaleDetail.setProductId(productId);
        saveSaleDetail.setProductName(productName);
        saveSaleDetail.setProductValue(productValue);
        saveSaleDetail.setQuantity(quantity);
        saveSaleDetail.setAmount(amount);
        saleDetailRepository.save(saveSaleDetail);

    }

}
