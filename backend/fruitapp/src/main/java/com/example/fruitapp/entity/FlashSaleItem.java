package com.example.fruitapp.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Table(name = "flash_sale_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlashSaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flash_sale_id")
    private FlashSale flashSale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "original_price")
    private BigDecimal orignalPrice;

    @Column(name = "flash_sale_price")
    private BigDecimal flashSalePrice;

    private Integer discount;
    private Integer quantity;
    private Integer sold;

    @Column(name = "max_quantity_per_user")
    private Integer maxQuantityPerUser;

    
}
