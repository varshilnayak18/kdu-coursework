package com.kdu.smarthome.service;

import com.kdu.smarthome.constants.UserRole;
import com.kdu.smarthome.dto.request.AddRoomRequestDTO;
import com.kdu.smarthome.dto.response.RoomResponseDTO;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exception.custom.DataNotFoundException;
import com.kdu.smarthome.exception.custom.UnauthorizedAccessException;
import com.kdu.smarthome.exception.custom.UnprocessableEntityException;
import com.kdu.smarthome.repo.HouseOwnerRepository;
import com.kdu.smarthome.repo.HouseRepository;
import com.kdu.smarthome.repo.RoomRepository;
import com.kdu.smarthome.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service class for managing operations related to rooms in a house.
 */
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;
    private final JwtService jwtService;
    private final HouseOwnerRepository houseOwnerRepository;
    @Autowired
    public RoomService(RoomRepository roomRepository, HouseRepository houseRepository,
                       JwtService jwtService, HouseOwnerRepository houseOwnerRepository) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
        this.jwtService = jwtService;
        this.houseOwnerRepository = houseOwnerRepository;
    }

    /**
     * Adds a room to a given house.
     *
     * @param houseId              The ID of the house to which the room will be added.
     * @param addRoomRequestDTO    The DTO containing information about the room to be added.
     * @param token                The JWT token for authentication.
     * @return The response DTO containing information about the added room.
     */
    public RoomResponseDTO addRoomToHouse(String houseId, AddRoomRequestDTO addRoomRequestDTO, String token) {
        try {
            String username = jwtService.extractUsername(token.substring(7));
            if (!(houseOwnerRepository.findByHouse_IdAndUser_Username(houseId, username)
                    .getRole().equals(UserRole.ADMIN))) {
                throw new UnauthorizedAccessException("User is not the admin of the house");
            }
            House house = houseRepository.findById(houseId).orElse(null);
            if (Objects.isNull(house)) {
                throw new DataNotFoundException("House with given id does not exist");
            }
            Room room = Mapper.mapToRoom(house, addRoomRequestDTO);
            return Mapper.mapToRoomResponseDTO(roomRepository.save(room), "Room added to given house");
        } catch (UnauthorizedAccessException e) {
            throw new UnauthorizedAccessException("Room cannot be added. " + e.getMessage());
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException("Room cannot be added. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("Room cannot be added please check again");
        }
    }
}
