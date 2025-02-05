package com.example.webregister.service.product;

import com.example.webregister.model.Product;
import com.example.webregister.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchByUserId(Long userId) {

        List<Product> productList;
        productList = productRepository.findByUserIdAndIsDeletedFalseOrderByProductIdAsc(userId);
        if (productList.isEmpty()) {
            System.out.println("No products found");
        }
        return productList;

    }

    public List<Product> searchAll() {

        List<Product> productList;
        productList = productRepository.findAllByIsDeletedFalseOrderByProductIdAsc();
        if (productList.isEmpty()) {
            System.out.println("No products found");
        }
        return productList;

    }

    public void deleteByProductId(Long productId) {

        productRepository.logicallyDeleteByProductId(productId);

    }

}