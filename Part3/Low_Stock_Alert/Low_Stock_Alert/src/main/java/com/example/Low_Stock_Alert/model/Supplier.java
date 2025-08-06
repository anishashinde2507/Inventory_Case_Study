package com.example.Low_Stock_Alert.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
