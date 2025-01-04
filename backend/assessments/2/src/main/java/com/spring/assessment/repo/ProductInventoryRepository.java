package com.spring.assessment.repo;

import com.spring.assessment.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory,Long> {
    @Query(value = "update product_inventory c set c.quantity = :quantity WHERE c.product_id = :productId", nativeQuery = true)
    void update(@Param("productId") long productId,@Param("quantity") long quantity);
}
