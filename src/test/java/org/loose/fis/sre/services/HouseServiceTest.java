package org.loose.fis.sre.services;

import static org.junit.jupiter.api.Assertions.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.HouseService;
import org.loose.fis.sre.model.House;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class HouseServiceTest {
    public static final String ADDRESS="Address";
    public static final String SIZE="Size";
    public static final String ROOMS="Rooms";
    public static final String BATHS="Baths";
    public static final String FLOORS="Floors";
    public static final String SPECIAL="Special";
    public static final String MOD="Modify";

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

    @BeforeAll
    static void beforeAll() throws Exception{
        FileSystemService.HOUSE_FOLDER = ".test-addhouses";
        FileSystemService.initDirectory_house();
        FileUtils.cleanDirectory(FileSystemService.getHouseHomeFolder().toFile());
        HouseService.initDatabase();
        System.out.println("Before class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After class");
    }

    @Test
    @Order(1)
    @DisplayName("Database is initialized and there are no houses")
    void testDataBaseIsInitializedAndNoHousesIsPersisted() {
        assertThat(HouseService.getAllHouses()).isNotNull();
        assertThat(HouseService.getAllHouses()).isEmpty();
        System.out.println("1");
    }

    @Test
    @Order(2)
    @DisplayName("House is successfully persisted to Database")
    void testHouseIsAddedToDatabase() throws AddressAlreadyExistsException {
        HouseService.addHouse(ADDRESS, SIZE, ROOMS,BATHS,FLOORS, SPECIAL);
        assertThat(HouseService.getAllHouses()).isNotEmpty();
        assertThat(HouseService.getAllHouses()).size().isEqualTo(1);
        House house = HouseService.getAllHouses().get(0);
        assertThat(house).isNotNull();
        assertThat(house.getAddress()).isEqualTo(ADDRESS);
        assertThat(house.getSize()).isEqualTo(SIZE);
        assertThat(house.getRooms()).isEqualTo(ROOMS);
        assertThat(house.getBaths()).isEqualTo(BATHS);
        assertThat(house.getFloors()).isEqualTo(FLOORS);
        assertThat(house.getSpecial()).isEqualTo(SPECIAL);
        System.out.println("2");
    }

    @Test
    @Order(3)
    @DisplayName("House can not be added twice")
    void testHouseCanNotBeAddedTwice() {
        assertThrows(AddressAlreadyExistsException.class, () -> {
            HouseService.addHouse(ADDRESS, SIZE, ROOMS,BATHS,FLOORS, SPECIAL);
            HouseService.addHouse(ADDRESS, SIZE, ROOMS,BATHS,FLOORS, SPECIAL);
        });
        System.out.println("3");
    }

    @Test
    @Order(4)
    @DisplayName("Address must be unique")
    void testAddressDoesNotAlreadyExist() {
        assertThrows(AddressAlreadyExistsException.class, () -> {
            HouseService.addHouse(ADDRESS, SIZE, ROOMS,BATHS,FLOORS, SPECIAL);
            HouseService.checkAddressDoesNotAlreadyExist(ADDRESS);
        });
        System.out.println("4");
    }

    @Test
    @Order(5)
    @DisplayName("House list is correct")
    void testSeeHouses()  {
        assertThat(HouseService.seeHouses()).isEqualTo("Address=Address, Size= Size, Rooms= Rooms, Baths= Baths, Floors= Floors, Special= Special\n");
    }

    @Test
    @Order(6)
    @DisplayName("House can be edited")
    void testEditHouse() throws HouseDoesNotExistsException {
        HouseService.editHouse(ADDRESS,MOD,MOD,MOD,MOD,MOD);
        House house = HouseService.getAllHouses().get(0);
        org.assertj.core.api.Assertions.assertThat(house.getAddress()).isEqualTo(ADDRESS);
        org.assertj.core.api.Assertions.assertThat(house.getSize()).isEqualTo(MOD);
        org.assertj.core.api.Assertions.assertThat(house.getRooms()).isEqualTo(MOD);
        org.assertj.core.api.Assertions.assertThat(house.getBaths()).isEqualTo(MOD);
        org.assertj.core.api.Assertions.assertThat(house.getFloors()).isEqualTo(MOD);
        org.assertj.core.api.Assertions.assertThat(house.getSpecial()).isEqualTo(MOD);
        System.out.println("6");
    }

    @Test
    @Order(7)
    @DisplayName("House can't be edited because it is not found")
    void testHouseEditNotFound() {
        assertThrows(HouseDoesNotExistsException.class, () -> {
            HouseService.editHouse("address",MOD,MOD,MOD,MOD,MOD);
            HouseService.checkAddressDoesExist("address");
        });
        System.out.println("7");
    }
}

