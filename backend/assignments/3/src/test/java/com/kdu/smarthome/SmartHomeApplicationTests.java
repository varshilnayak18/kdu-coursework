package com.kdu.smarthome;

import com.kdu.smarthome.controller.AuthControllerTest;
import com.kdu.smarthome.controller.DeviceControllerTest;
import com.kdu.smarthome.controller.HouseControllerTest;
import com.kdu.smarthome.controller.InventoryControllerTest;
import com.kdu.smarthome.controller.RoomControllerTest;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class SmartHomeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Run authentication related tests.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(1)
    void runAuthTests() throws Exception {
        MvcResult mvcResult = AuthControllerTest.registerUser(mockMvc);

        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    /**
     * Run house related tests.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(2)
    void houseRegisterWithInvalidAuth() throws Exception {
        MvcResult result = HouseControllerTest.houseRegisterWithInvalidAuth(mockMvc);
        Assert.assertEquals(403, result.getResponse().getStatus());
    }

    @Test
    @Order(3)
    void houseRegisterWithValidRequestData() {
        MvcResult result = HouseControllerTest.houseRegisterWithValidRequestData(mockMvc);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    /**
     * Run adding users to house related tests.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(4)
    void addUnregisteredUserToHouseByAdmin() throws Exception {
        MvcResult result = HouseControllerTest.addUnregisteredUserToHouseByAdmin(mockMvc);
        Assert.assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    @Order(5)
    void addUserToHouseByNonAdmin() throws Exception {
        MvcResult result = HouseControllerTest.addUserToHouseByNonAdmin(mockMvc);
        Assert.assertEquals(401, result.getResponse().getStatus());
    }

    @Test
    @Order(6)
    void addUserToHouseByAdmin() throws Exception {
        MvcResult result = HouseControllerTest.addUserToHouseByAdmin(mockMvc);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    /**
     * Run adding room to house related tests.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(7)
    void addRoomWithInvalidAuth() throws Exception {
        MvcResult result = RoomControllerTest.addRoomWithInvalidAuth(mockMvc);
        Assert.assertEquals(403, result.getResponse().getStatus());
    }

    @Test
    @Order(8)
    void addRoomByAdmin() throws Exception {
        MvcResult result = RoomControllerTest.addRoomByAdmin(mockMvc);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }


    @Test
    @Order(9)
    void addRoomForInvalidHouse() throws Exception {
        MvcResult result = RoomControllerTest.addRoomForInvalidHouse(mockMvc);
        Assert.assertEquals(400, result.getResponse().getStatus());
    }

    /**
     * Tests to display all registered houses.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(10)
    void displayAllHouses() throws Exception {
        MvcResult result = HouseControllerTest.displayAllHouses(mockMvc);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(11)
    void displayAllHousesWithInvalidAuth() throws Exception {
        MvcResult result = HouseControllerTest.displayAllHousesWithInvalidAuth(mockMvc);
        Assert.assertEquals(403, result.getResponse().getStatus());
    }

    @Test
    @Order(11)
    void displayAllHousesByNonAdmin() throws Exception {
        MvcResult result = HouseControllerTest.displayAllHousesByNonAdmin(mockMvc);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    /**
     * Tests to update house address.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(12)
    void updateAddressForHouse() throws Exception {
        MvcResult result = HouseControllerTest.updateAddressForHouse(mockMvc);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(13)
    void updateAddressForInvalidHouse() throws Exception {
        MvcResult result = HouseControllerTest.updateAddressForInvalidHouse(mockMvc);
        Assert.assertEquals(400, result.getResponse().getStatus());
    }

    /**
     * Run inventory related tests
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(14)
    void addDeviceToInventory() throws Exception {
        MvcResult result = InventoryControllerTest.addDeviceToInventory(mockMvc);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(15)
    void displayInventory() throws Exception {
        MvcResult result = InventoryControllerTest.displayInventory(mockMvc);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    /**
     * Run device registration related tests
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(16)
    void registerUnavailableDevice() throws Exception {
        MvcResult result = DeviceControllerTest.registerUnavailableDevice(mockMvc);
        Assert.assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    @Order(17)
    void deviceRegisterWithInvalidCredentials() throws Exception {
        MvcResult result = DeviceControllerTest.deviceRegisterWithInvalidCredentials(mockMvc);
        Assert.assertEquals(401, result.getResponse().getStatus());
    }

    @Test
    @Order(18)
    void deviceRegisterByNonAdmin() throws Exception {
        MvcResult result = DeviceControllerTest.deviceRegisterByNonAdmin(mockMvc);
        Assert.assertEquals(401, result.getResponse().getStatus());
    }

    @Test
    @Order(19)
    void deviceRegisterWithValidRequestData() throws Exception {
        MvcResult result = DeviceControllerTest.deviceRegisterWithValidRequestData(mockMvc);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    /**
     * Tests to add device in a house
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(20)
    void addUnavailableDevice() throws Exception {
        MvcResult result = DeviceControllerTest.addUnavailableDevice(mockMvc);
        Assert.assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    @Order(21)
    void addDeviceToInvalidHouse() throws Exception {
        MvcResult result = DeviceControllerTest.addDeviceToInvalidHouse(mockMvc);
        Assert.assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    @Order(22)
    void addDeviceToInvalidRoom() throws Exception {
        MvcResult result = DeviceControllerTest.addDeviceToInvalidRoom(mockMvc);
        Assert.assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    @Order(23)
    void addUnregisteredDevice() throws Exception {
        MvcResult result = DeviceControllerTest.addUnregisteredDevice(mockMvc);
        Assert.assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    @Order(24)
    void addDeviceWithValidRequestData() throws Exception {
        MvcResult result = DeviceControllerTest.addDeviceWithValidRequestData(mockMvc);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    /**
     * Tests to display all rooms and devices for a house
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(25)
    void listRoomsAndDevices() throws Exception {
        MvcResult result = HouseControllerTest.listRoomsAndDevices(mockMvc);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }
}
