package com.example.Low_Stock_Alert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LowStockAlertDTO {
    private Long productId;
    private String productName;
    private String sku;
    private Long warehouseId;
    private String warehouseName;
    private Integer currentStock;
    private Integer threshold;
    private Integer daysUntilStockout;
    private SupplierDTO supplier;
}
