package com.spring.assessment.controller;

import com.spring.assessment.entity.CartProduct;
import com.spring.assessment.entity.ProductInventory;
import com.spring.assessment.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductInventoryController {
    private ProductInventoryService productInventoryService;
    @Autowired
    public ProductInventoryController(ProductInventoryService productInventoryService) {
        this.productInventoryService = productInventoryService;
    }

    @PostMapping
    public ResponseEntity<String> addProductInventory(@RequestBody ProductInventory productInventory){
        productInventoryService.addProductInventory(productInventory);
        return ResponseEntity.ok("Product added to Inventory successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateProductQuantity(@RequestParam long productId, @RequestBody ProductInventory productInventory){
        productInventoryService.updateProductQuantity(productId,productInventory);
        return ResponseEntity.ok("Product quantity updated successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam long productId){
        productInventoryService.deleteproduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
