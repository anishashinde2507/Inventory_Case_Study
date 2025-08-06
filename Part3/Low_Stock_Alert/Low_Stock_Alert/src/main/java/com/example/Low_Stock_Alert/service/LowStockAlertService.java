package com.example.Low_Stock_Alert.service;

import com.example.Low_Stock_Alert.dto.LowStockAlertDTO;
import com.example.Low_Stock_Alert.dto.SupplierDTO;
import com.example.Low_Stock_Alert.model.Inventory;
import com.example.Low_Stock_Alert.model.Product;
import com.example.Low_Stock_Alert.model.Supplier;
import com.example.Low_Stock_Alert.model.Warehouse;
import com.example.Low_Stock_Alert.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LowStockAlertService {

    private final InventoryRepository inventoryRepository;

    public List<LowStockAlertDTO> getLowStockAlerts(Long companyId) {
        // Calculate date threshold (last 90 days)
        LocalDate dateThreshold = LocalDate.now().minusDays(90);

        // Pass both parameters to repository
        List<Inventory> lowStockList = inventoryRepository.findLowStockWithRecentSales(companyId, dateThreshold);

        return lowStockList.stream().map(inv -> {
            Product p = inv.getProduct();
            Warehouse w = inv.getWarehouse();

            LowStockAlertDTO dto = new LowStockAlertDTO();
            dto.setProductId(p.getId());
            dto.setProductName(p.getName());
            dto.setSku(p.getSku());
            dto.setWarehouseId(w.getId());
            dto.setWarehouseName(w.getName());
            dto.setCurrentStock(inv.getQuantity());
            dto.setThreshold(p.getLowStockThreshold());

            // Example: estimated daily sales
            int avgDailySales = 2;
            dto.setDaysUntilStockout(inv.getQuantity() / avgDailySales);

            if (!p.getSuppliers().isEmpty()) {
                Supplier s = p.getSuppliers().iterator().next();
                SupplierDTO supplierDTO = new SupplierDTO();
                supplierDTO.setId(s.getId());
                supplierDTO.setName(s.getName());
                supplierDTO.setContactEmail(s.getContactEmail());
                dto.setSupplier(supplierDTO);
            }

            return dto;
        }).toList();
    }
}
