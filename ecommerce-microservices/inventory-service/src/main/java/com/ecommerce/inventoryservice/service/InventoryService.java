package com.ecommerce.inventoryservice.service;
import com.ecommerce.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service @RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    public boolean checkStock(List<String> skuCodes) {
        var inventory = inventoryRepository.findBySkuCodeIn(skuCodes);
        return inventory.stream().allMatch(i -> i.getQuantity() > 0);
    }
}
