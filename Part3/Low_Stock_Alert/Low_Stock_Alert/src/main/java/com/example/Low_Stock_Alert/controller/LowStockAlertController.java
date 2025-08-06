package com.example.Low_Stock_Alert.controller;


import com.example.Low_Stock_Alert.dto.LowStockAlertDTO;
import com.example.Low_Stock_Alert.service.LowStockAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class LowStockAlertController {

    @Autowired
    private LowStockAlertService alertService;

    @GetMapping("/{companyId}/alerts/low-stock")
    public ResponseEntity<Map<String, Object>>getLowStockAlerts(@PathVariable Long companyId) {
        List<LowStockAlertDTO> alerts = alertService.getLowStockAlerts(companyId);

        Map<String, Object> response = new HashMap<>();
        if (alerts.isEmpty()) {
            response.put("message", "No low stock products found");
            response.put("total_alerts", 0);
        } else {
            response.put("alerts", alerts);
            response.put("total_alerts", alerts.size());
        }

        return ResponseEntity.ok(response);
    }
}
