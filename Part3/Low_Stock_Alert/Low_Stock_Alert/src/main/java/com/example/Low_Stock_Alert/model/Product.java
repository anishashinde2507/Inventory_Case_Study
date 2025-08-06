package com.example.Low_Stock_Alert.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String sku;
        private Integer lowStockThreshold;
        private BigDecimal price;

        @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
        private Set<Supplier> suppliers = new HashSet<>();

}
