package com.example.Low_Stock_Alert.repository;


import com.example.Low_Stock_Alert.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("""
        SELECT DISTINCT i
        FROM Inventory i
        JOIN FETCH i.product p
        JOIN FETCH i.warehouse w
        LEFT JOIN FETCH p.suppliers s
        WHERE w.company.id = :companyId
          AND i.quantity < p.lowStockThreshold
          AND EXISTS (
              SELECT 1
              FROM Sale sale
              WHERE sale.product = p
              AND sale.saleDate >= :dateThreshold
          )
    """)
    List<Inventory> findLowStockWithRecentSales(
            @Param("companyId") Long companyId,
            @Param("dateThreshold") LocalDate dateThreshold
    );
}
