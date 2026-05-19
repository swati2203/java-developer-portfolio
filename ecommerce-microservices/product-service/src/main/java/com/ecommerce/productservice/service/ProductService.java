package com.ecommerce.productservice.service;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service @RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getAllProducts() { return productRepository.findAll(); }
    public Product createProduct(Product product) { return productRepository.save(product); }
    public Product getBySkuCode(String sku) {
        return productRepository.findBySkuCode(sku)
            .orElseThrow(() -> new RuntimeException("Product not found: " + sku));
    }
}
