package com.example.webregister.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long saleId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "receive_amount")
    private Integer receiveAmount;

    @Column(name = "change_amount")
    private Integer changeAmount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}