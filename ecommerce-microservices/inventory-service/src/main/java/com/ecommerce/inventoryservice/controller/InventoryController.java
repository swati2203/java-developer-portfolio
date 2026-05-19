package com.ecommerce.inventoryservice.controller;
import com.ecommerce.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/inventory") @RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkStock(@RequestParam List<String> skuCodes) {
        return ResponseEntity.ok(inventoryService.checkStock(skuCodes));
    }
}
