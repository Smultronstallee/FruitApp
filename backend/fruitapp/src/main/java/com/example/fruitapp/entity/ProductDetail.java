package com.example.fruitapp.entity;
import java.time.LocalDate;

import org.springframework.format.annotation.DurationFormat.Unit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String origin;

    @Column(name = "freshness_percent")
    private Integer freshnessPercent;

    @Enumerated(EnumType.STRING)
    private PackageType packaging;

    @Column(name = "storage_instruction")
    private String storageInstruction;

    private Double weight;

    @Enumerated(EnumType.STRING)
    private Unit unit;
    
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
}
