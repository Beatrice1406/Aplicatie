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
}
