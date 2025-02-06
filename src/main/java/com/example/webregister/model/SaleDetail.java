package com.example.webregister.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sale_details")
@Data
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_detail_id")
    private Long saleDetailId;

    @Column(name = "sale_id")
    private Long saleId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_value")
    private Integer productValue;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private Integer amount;

}