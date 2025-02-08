package com.example.webregister.service;

import com.example.webregister.model.Sale;
import com.example.webregister.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public Long insertSale(Long userId, int totalAmount, int receiveAmount, int changeAmount) {

        Sale saveSale = new Sale();
        Sale savedSale;
        saveSale.setUserId(userId);
        saveSale.setTotalAmount(totalAmount);
        saveSale.setReceiveAmount(receiveAmount);
        saveSale.setChangeAmount(changeAmount);
        saveSale.setCreatedAt(LocalDateTime.now());
        savedSale = saleRepository.save(saveSale);
        return savedSale.getSaleId();

    }

    public List<Sale> searchAllByUserId(Long userId) {

        List<Sale> saleList;
        saleList = saleRepository.findByUserIdOrderBySaleIdAsc(userId);
        if (saleList.isEmpty()) {
            System.out.println("No sales found");
        }
        return saleList;

    }

    public void deleteBySaleId(Long saleId) {

        saleRepository.deleteBySaleId(saleId);

    }

}