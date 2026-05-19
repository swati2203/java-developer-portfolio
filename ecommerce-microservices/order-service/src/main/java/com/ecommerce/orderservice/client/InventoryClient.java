package com.ecommerce.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "inventory-service", url = "http://localhost:8083")
public interface InventoryClient {

    @GetMapping("/api/inventory/check")
    boolean checkStock(@RequestParam List<String> skuCodes);
}