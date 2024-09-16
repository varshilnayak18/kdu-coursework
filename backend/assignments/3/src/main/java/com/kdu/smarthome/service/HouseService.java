package com.kdu.smarthome.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.constants.UserRole;
import com.kdu.smarthome.dto.request.HouseRegisterRequestDTO;
import com.kdu.smarthome.dto.request.UpdateAddressRequestDTO;
import com.kdu.smarthome.dto.request.AddUserToHouseRequestDTO;
import com.kdu.smarthome.dto.response.GetAllHousesResponseDTO;
import com.kdu.smarthome.dto.response.HouseRegisterResponseDTO;
import com.kdu.smarthome.dto.response.ResponseDTO;
import com.kdu.smarthome.dto.response.GetRoomsDevicesResponseDTO;
import com.kdu.smarthome.entity.*;
import com.kdu.smarthome.exception.custom.DataNotFoundException;
import com.kdu.smarthome.exception.custom.FetchFailedException;
import com.kdu.smarthome.exception.custom.UnauthorizedAccessException;
import com.kdu.smarthome.exception.custom.UnprocessableEntityException;
import com.kdu.smarthome.repo.*;
import com.kdu.smarthome.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service class for handling house-related operations.
 */
@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final HouseOwnerRepository houseOwnerRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final DeviceRepository deviceRepository;
    private final JwtService jwtService;

    String userNotExist = "User with given username does not exist";
    String houseNotExist = "House with given id does not exist";
    @Autowired
    public HouseService(HouseRepository houseRepository, HouseOwnerRepository houseOwnerRepository,
                        UserRepository userRepository, RoomRepository roomRepository, DeviceRepository deviceRepository,
                        JwtService jwtService) {
        this.houseRepository = houseRepository;
        this.houseOwnerRepository = houseOwnerRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
        this.jwtService = jwtService;
    }

    /**
     * Adds a new house.
     *
     * @param houseRegisterRequestDTO   DTO containing details for registering a new house.
     * @param token                     JWT token for user authentication.
     * @return                          HouseRegisterResponseDTO with the result of the house registration.
     * @throws UnprocessableEntityException If house registration fails.
     */
    public HouseRegisterResponseDTO addHouse(HouseRegisterRequestDTO houseRegisterRequestDTO, String token) {
        try {
            User user = userRepository.findById(jwtService.extractUsername(token.substring(7))).orElse(null);
            if(Objects.isNull(user)){
                throw new DataNotFoundException(userNotExist);
            }
            House house = houseRepository.save(Mapper.mapToHouse(houseRegisterRequestDTO));
            houseOwnerRepository.save(Mapper.mapToHouseOwner(house,user, UserRole.ADMIN));
            return Mapper.mapToHouseRegisterResponseDTO(house,"House added successfully");
        }
        catch (DataNotFoundException e){
            throw new DataNotFoundException("House cannot be added. " + e.getMessage());
        }
        catch (Exception e){
            throw new UnprocessableEntityException("House cannot be added please check again");
        }
    }

    /**
     * Adds a user to a house.
     *
     * @param houseId                     ID of the house.
     * @param addUserToHouseRequestDTO    DTO containing details for adding a user to a house.
     * @param token                       JWT token for user authentication.
     * @return                            ResponseDTO with the result of adding the user to the house.
     * @throws JsonProcessingException    If there is an issue processing JSON.
     * @throws UnprocessableEntityException If adding the user to the house fails.
     */
    public ResponseDTO addUserToHouse(String houseId, AddUserToHouseRequestDTO addUserToHouseRequestDTO, String token) throws JsonProcessingException {
        try {
            User admin = userRepository.findById(jwtService.extractUsername(token.substring(7))).orElse(null);
            User user = userRepository.findById(addUserToHouseRequestDTO.getUsername()).orElse(null);
            if(Objects.isNull(admin) || Objects.isNull(user)){
                throw new DataNotFoundException(userNotExist);
            }
            House house = houseRepository.findById(houseId).orElse(null);
            if(Objects.isNull(house)){
                throw new DataNotFoundException(houseNotExist);
            }
            HouseOwner houseOwner = houseOwnerRepository.findByHouse_IdAndUser_Username(houseId,admin.getUsername());
            if(!(Objects.isNull(houseOwner)) && houseOwner.getRole().equals(UserRole.ADMIN)){
                houseOwnerRepository.save(Mapper.mapToHouseOwner(house,user, UserRole.USER));
            }
            else {
                throw new UnauthorizedAccessException("User with given username is not admin of the house");
            }
            return Mapper.mapToResponseDTO(house,"User added to house successfully");
        }
        catch (DataNotFoundException e) {
            throw new DataNotFoundException("User cannot be added. " + e.getMessage());
        } catch (UnauthorizedAccessException e) {
            throw new UnauthorizedAccessException("User cannot be added. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("User cannot be added please check again");
        }
    }

    /**
     * Retrieves a list of all houses.
     *
     * @return      GetAllHousesResponseDTO with the list of houses and a success message.
     * @throws FetchFailedException If fetching the house list fails.
     */
    public GetAllHousesResponseDTO getAllHouses() {
        try {
            List<House> houseList = houseRepository.findAll();
            return Mapper.mapToGetAllHousesResponseDTO(houseList,"House List fetched successfully");
        }
        catch (Exception e){
            throw new FetchFailedException("Failed to fetch house list");
        }
    }


    /**
     * Updates the address of a house.
     *
     * @param houseId     ID of the house to be updated.
     * @param houseDTO    DTO containing the updated address.
     * @param token       JWT token for user authentication.
     * @return            ResponseDTO with the result of updating the house address.
     * @throws DataNotFoundException        If the house or user does not exist.
     * @throws UnauthorizedAccessException  If the user is not the owner of the house.
     * @throws UnprocessableEntityException If updating the house address fails.
     */
    public ResponseDTO updateHouseAddress(String houseId, UpdateAddressRequestDTO houseDTO, String token) {
        try {
            User user = userRepository.findById(jwtService.extractUsername(token.substring(7))).orElse(null);
            if(Objects.isNull(user)){
                throw new DataNotFoundException(userNotExist);
            }
            House house = houseRepository.findById(houseId).orElse(null);
            if(Objects.isNull(house)){
                throw new DataNotFoundException(houseNotExist);
            }
            HouseOwner houseOwner = houseOwnerRepository.findByHouse_IdAndUser_Username(houseId,user.getUsername());
            if(Objects.isNull(houseOwner)){
                throw new UnauthorizedAccessException("User with given username is not owner of the house");
            }
            house.setAddress(houseDTO.getAddress());
            house = houseRepository.save(house);
            return Mapper.mapToResponseDTO(house,"House address updated successfully");
        }
        catch (DataNotFoundException e) {
            throw new DataNotFoundException("House cannot be updated. " + e.getMessage());
        } catch (UnauthorizedAccessException e) {
            throw new UnauthorizedAccessException("House cannot be updated. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("House cannot be updated please check again");
        }
    }

    /**
     * Retrieves details of a house including its rooms and devices.
     *
     * @param houseId     ID of the house to fetch details for.
     * @return            GetRoomsDevicesResponseDTO with the details of the house.
     * @throws DataNotFoundException        If the house does not exist.
     * @throws UnprocessableEntityException If fetching house details fails.
     */
    public GetRoomsDevicesResponseDTO getHouseDetails(String houseId) {
        try {
            House house = houseRepository.findById(houseId).orElse(null);
            if(Objects.isNull(house)){
                throw new DataNotFoundException(houseNotExist);
            }
            List<Room> roomList = roomRepository.findByHouse_Id(houseId);
            List<Device> deviceList = deviceRepository.findByHouse_Id(houseId);
            List<Object> houseList = new ArrayList<>();
            houseList.addAll(roomList);
            houseList.addAll(deviceList);
            return Mapper.mapToGetRoomsDevicesResponseDTO(houseList,"House details fetched successfully");

        }
        catch (DataNotFoundException e) {
            throw new DataNotFoundException("House details cannot be fetched. " + e.getMessage());
        } catch (Exception e){
            throw new UnprocessableEntityException("House details cannot be fetched please check again");
        }
    }
}
