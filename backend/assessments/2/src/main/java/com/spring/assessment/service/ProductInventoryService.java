package com.spring.assessment.service;

import com.spring.assessment.entity.ProductInventory;
import com.spring.assessment.exception.custom.DataNotFoundException;
import com.spring.assessment.exception.custom.UnprocessableEntityException;
import com.spring.assessment.repo.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductInventoryService {
    private ProductInventoryRepository productInventoryRepository;
    @Autowired
    public ProductInventoryService(ProductInventoryRepository productInventoryRepository) {
        this.productInventoryRepository = productInventoryRepository;
    }


    public void addProductInventory(ProductInventory productInventory) {
        try {
            productInventoryRepository.save(productInventory);
        }
        catch (Exception e){
            throw new UnprocessableEntityException("Product cannot be added please check the entity again.");
        }
    }

    public void updateProductQuantity(long productId, ProductInventory productInventory) {
        try {
            productInventoryRepository.update(productId,productInventory.getQuantity());
        }
        catch (Exception e){
            throw new UnprocessableEntityException("Product cannot be added please check the entity again.");
        }
    }

    public void deleteproduct(Long productId) {
        try {
            Optional<ProductInventory> productInventory = productInventoryRepository.findById(productId);
            if(!(productInventory.isPresent())){
                throw new DataNotFoundException("No product with given Id exists");
            }
            else {
                productInventoryRepository.delete(productInventory.get());
            }
        }
        catch (Exception e){
            throw new DataNotFoundException("No product with given Id exists");
        }
    }
}
