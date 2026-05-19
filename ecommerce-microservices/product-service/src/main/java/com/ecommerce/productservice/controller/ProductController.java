package com.ecommerce.productservice.controller;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/products") @RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping public ResponseEntity<List<Product>> getAll() { return ResponseEntity.ok(productService.getAllProducts()); }
    @PostMapping public ResponseEntity<Product> create(@RequestBody Product p) { return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(p)); }
    @GetMapping("/sku/{sku}") public ResponseEntity<Product> getBySku(@PathVariable String sku) { return ResponseEntity.ok(productService.getBySkuCode(sku)); }
}
