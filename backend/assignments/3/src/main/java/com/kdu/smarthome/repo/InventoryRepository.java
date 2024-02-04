package com.kdu.smarthome.repo;

import com.kdu.smarthome.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Inventory entities in the database.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory,String> {
}
