package com.kdu.smarthome.repo;

import com.kdu.smarthome.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing House entities in the database.
 */
@Repository
public interface HouseRepository extends JpaRepository<House, String> {
}
