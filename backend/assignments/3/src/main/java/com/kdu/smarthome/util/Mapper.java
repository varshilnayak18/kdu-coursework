package com.kdu.smarthome.util;

import com.kdu.smarthome.constants.UserRole;
import com.kdu.smarthome.dto.request.*;
import com.kdu.smarthome.dto.response.*;
import com.kdu.smarthome.entity.*;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Utility class for mapping DTOs, entities, and responses.
 */
public class Mapper {
    private Mapper() {
    }

    /**
     * Maps a DeviceRegisterRequestDTO to a Device entity.
     */
    public static Device mapToDevice(DeviceRegisterRequestDTO deviceRegisterRequestDTO){
        return Device.builder()
                .kickstonId(deviceRegisterRequestDTO.getKickstonId())
                .deviceUsername(deviceRegisterRequestDTO.getDeviceUsername())
                .devicePassword(deviceRegisterRequestDTO.getDevicePassword())
                .build();
    }

    /**
     * Maps an Object to a ResponseDTO entity.
     */
    public static ResponseDTO mapToResponseDTO(Object object, String message) {
        return ResponseDTO.builder()
                .message(message)
                .object(object.toString())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Maps a HouseRegisterRequestDTO to a House entity.
     */
    public static House mapToHouse(HouseRegisterRequestDTO houseRegisterRequestDTO){
        return House.builder()
                .address(houseRegisterRequestDTO.getAddress())
                .houseName(houseRegisterRequestDTO.getHouseName())
                .build();
    }

    /**
     * Maps a House, User and role to a HouseOwner entity.
     */
    public static HouseOwner mapToHouseOwner(House house, User user, UserRole userRole) {
        return HouseOwner.builder()
                .house(house)
                .user(user)
                .role(userRole)
                .build();
    }

    /**
     * Maps an AddRoomRequestDTO to a Room entity.
     */
    public static Room mapToRoom(House house, AddRoomRequestDTO addRoomRequestDTO) {
        return Room.builder()
                .roomName(addRoomRequestDTO.getRoomName())
                .house(house)
                .build();
    }

    /**
     * Maps an UserRegisterRequestDTO to an User entity.
     */
    public static User mapToUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        return User.builder()
                .username(userRegisterRequestDTO.getUsername())
                .password(userRegisterRequestDTO.getPassword())
                .name(userRegisterRequestDTO.getName())
                .firstName(userRegisterRequestDTO.getFirstName())
                .lastName(userRegisterRequestDTO.getLastName())
                .emailId(userRegisterRequestDTO.getEmailId())
                .build();
    }

    /**
     * Maps a InventoryRequestDTO to an Inventory entity.
     */
    public static Inventory mapToInventory(InventoryRequestDTO inventoryRequestDTO) {
        return Inventory.builder()
                .kickstonId(inventoryRequestDTO.getKickstonId())
                .deviceUsername(inventoryRequestDTO.getDeviceUsername())
                .devicePassword(inventoryRequestDTO.getDevicePassword())
                .manufactureDateTime(inventoryRequestDTO.getManufactureDateTime())
                .manufactureFactoryPlace(inventoryRequestDTO.getManufactureFactoryPlace())
                .build();
    }

    /**
     * Maps a House to a HouseRegisterResponseDTO entity.
     */
    public static HouseRegisterResponseDTO mapToHouseRegisterResponseDTO(House house, String message) {
        return HouseRegisterResponseDTO.builder()
                .message(message)
                .house(house)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Maps InventoryList to InventoryResponseDTO entity.
     */
    public static InventoryResponseDTO mapToInventoryResponseDTO(List<Inventory> inventoryList) {
        return InventoryResponseDTO.builder()
                .inventory(inventoryList.toString())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Maps a HouseList to a GetAllHousesResponseDTO entity.
     */
    public static GetAllHousesResponseDTO mapToGetAllHousesResponseDTO(List<House> houseList, String message) {
        return GetAllHousesResponseDTO.builder()
                .houses(houseList.toString())
                .message(message)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Maps a Room to a RoomResponseDTO entity.
     */
    public static RoomResponseDTO mapToRoomResponseDTO(Room room, String message)  {
        return RoomResponseDTO.builder()
                .message(message)
                .room(room)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Maps a List to a GetRoomsDevicesResponseDTO entity.
     */
    public static GetRoomsDevicesResponseDTO mapToGetRoomsDevicesResponseDTO(List<Object> houseList, String message) {
        return GetRoomsDevicesResponseDTO.builder()
                .message(message)
                .roomsAndDevices(houseList.toString())
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
