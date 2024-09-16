package com.kdu.smarthome.repo;

import com.kdu.smarthome.entity.HouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing HouseOwner entities in the database.
 */
public interface HouseOwnerRepository extends JpaRepository<HouseOwner, String> {

    /**
     * Finds a HouseOwner by house ID and username.
     *
     * @param houseId  The ID of the house.
     * @param username The username of the user.
     * @return The HouseOwner associated with the specified house ID and username.
     */
    HouseOwner findByHouse_IdAndUser_Username(String houseId, String username);
}
