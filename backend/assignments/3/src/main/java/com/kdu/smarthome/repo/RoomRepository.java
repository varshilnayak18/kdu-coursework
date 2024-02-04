package com.kdu.smarthome.repo;

import com.kdu.smarthome.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Room entities in the database.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    /**
     * Retrieve a list of rooms by the house ID.
     *
     * @param id The ID of the house.
     * @return List of rooms associated with the specified house ID.
     */
    List<Room> findByHouse_Id(String id);
}
