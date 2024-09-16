package com.kdu.smarthome.service;

import com.kdu.smarthome.constants.UserRole;
import com.kdu.smarthome.dto.request.AddDeviceRequestDTO;
import com.kdu.smarthome.dto.request.DeviceRegisterRequestDTO;
import com.kdu.smarthome.dto.response.ResponseDTO;
import com.kdu.smarthome.entity.*;
import com.kdu.smarthome.exception.custom.DataNotFoundException;
import com.kdu.smarthome.exception.custom.UnauthorizedAccessException;
import com.kdu.smarthome.exception.custom.UnprocessableEntityException;
import com.kdu.smarthome.repo.*;
import com.kdu.smarthome.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service class for handling device-related operations.
 */
@Service
public class DeviceService {
    private DeviceRepository deviceRepository;
    private HouseRepository houseRepository;
    private RoomRepository roomRepository;
    private InventoryRepository inventoryRepository;
    private JwtService jwtService;
    private UserRepository userRepository;
    private HouseOwnerRepository houseOwnerRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, HouseRepository houseRepository, RoomRepository roomRepository,
                         InventoryRepository inventoryRepository, JwtService jwtService, UserRepository userRepository,
                         HouseOwnerRepository houseOwnerRepository) {
        this.deviceRepository = deviceRepository;
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.inventoryRepository = inventoryRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.houseOwnerRepository = houseOwnerRepository;
    }

    /**
     * Registers a new device.
     *
     * @param deviceRegisterRequestDTO   DTO containing device registration details.
     * @param token                      JWT token for user authentication.
     * @return                           ResponseDTO with the result of the device registration.
     * @throws UnprocessableEntityException If device registration fails.
     */
    public ResponseDTO registerDevice(DeviceRegisterRequestDTO deviceRegisterRequestDTO, String token) {
        try {
            String username = jwtService.extractUsername(token.substring(7));
            User user = userRepository.findById(username).orElse(null);
            if(Objects.isNull(user)){
                throw new UnauthorizedAccessException("Invalid username please check token");
            }
            Inventory inventory = inventoryRepository.findById(deviceRegisterRequestDTO.getKickstonId()).orElse(null);
            if(Objects.isNull(inventory)){
                throw new DataNotFoundException("Device with given id does not exist in inventory");
            }
            if(!(inventory.getDevicePassword().equals(deviceRegisterRequestDTO.getDevicePassword()) && inventory.getDeviceUsername().equals(deviceRegisterRequestDTO.getDeviceUsername()))){
                throw new UnauthorizedAccessException("Invalid credentials please check again");
            }
            return Mapper.mapToResponseDTO(deviceRepository.save(Mapper.mapToDevice(deviceRegisterRequestDTO)),"Device registered successfully");
        }
        catch (DataNotFoundException e) {
            throw new DataNotFoundException("Device cannot be registered. " + e.getMessage());
        } catch (UnauthorizedAccessException e) {
            throw new UnauthorizedAccessException("Device cannot be registered. " + e.getMessage());
        } catch (Exception e){
            throw new UnprocessableEntityException("Device cannot be registered please check again");
        }
    }

    /**
     * Adds a device to a house.
     *
     * @param addDeviceRequestDTO   DTO containing details for adding a device to a house.
     * @param token                 JWT token for user authentication.
     * @return                      ResponseDTO with the result of adding the device to the house.
     * @throws UnprocessableEntityException If adding the device to the house fails.
     */
    public ResponseDTO addDeviceToHouse(AddDeviceRequestDTO addDeviceRequestDTO,String token) {
        try {
            String username = jwtService.extractUsername(token.substring(7));
            User user = userRepository.findById(username).orElse(null);
            if(Objects.isNull(user)){
                throw new UnauthorizedAccessException("Invalid username please check token");
            }
            House house = houseRepository.findById(addDeviceRequestDTO.getHouseId()).orElse(null);
            if(Objects.isNull(house)){
                throw new DataNotFoundException("House with given id does not exist");
            }
            if(!(houseOwnerRepository.findByHouse_IdAndUser_Username(house.getId(),username).getRole().equals(UserRole.ADMIN))){
                throw new UnauthorizedAccessException("User is not the admin of the house");
            }
            Device device = deviceRepository.findById(addDeviceRequestDTO.getKickstonId()).orElse(null);
            if(Objects.isNull(device)){
                throw new DataNotFoundException("Device with given id does not exist");
            }
            else {
                Room room = roomRepository.findById(addDeviceRequestDTO.getRoomId()).orElse(null);
                if(Objects.isNull(room)){
                    throw new DataNotFoundException("Room with given id does not exist");
                }
                device.setHouse(house);
                device.setRoom(room);
                return Mapper.mapToResponseDTO(deviceRepository.save(device),"Device added to the given room of the house");
            }
        }
        catch (DataNotFoundException e) {
            throw new DataNotFoundException("Device cannot be added. " + e.getMessage());
        } catch (UnauthorizedAccessException e) {
            throw new UnauthorizedAccessException("Device cannot be added. " + e.getMessage());
        }
        catch (Exception e){
            throw new UnprocessableEntityException("Device cannot be added to house please check again");
        }
    }
}
