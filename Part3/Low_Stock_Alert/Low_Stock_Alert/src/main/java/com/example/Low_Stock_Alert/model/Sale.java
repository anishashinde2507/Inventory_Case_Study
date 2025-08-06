package com.example.Low_Stock_Alert.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private LocalDate saleDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}