package com.kdu.smarthome.repo;

import com.kdu.smarthome.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Device entities in the database.
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

    /**
     * Finds a list of devices associated with a specific house ID.
     *
     * @param houseId The ID of the house.
     * @return List of devices associated with the specified house ID.
     */
    List<Device> findByHouse_Id(String houseId);
}
