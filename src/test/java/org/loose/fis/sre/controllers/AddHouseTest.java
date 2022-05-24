
package org.loose.fis.sre.controllers;

import static org.junit.jupiter.api.Assertions.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.HouseService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;
@ExtendWith(ApplicationExtension.class)
class AddHouseTest {
    public static final String ADDRESS = "Address";
    public static final String SIZE = "size";
    public static final String ROOMS = "rooms";
    public static final String BATHS = "baths";
    public static final String FLOORS= "floors";
    public static final String SPECIAL = "special";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.HOUSE_FOLDER = ".test-see-addhouse";
        FileSystemService.initDirectory_house();
        FileUtils.cleanDirectory(FileSystemService.getHouseHomeFolder().toFile());
        HouseService.initDatabase();

    }
    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addhouse.fxml"));
        primaryStage.setTitle("Agentie Imobiliara");
        primaryStage.setScene(new Scene(root, 600, 575));
        primaryStage.show();
    }
    @Test
    void TestAddHouse(FxRobot robot) {
        robot.clickOn("#address");
        robot.write(ADDRESS);
        robot.clickOn("#size");
        robot.write(SIZE);
        robot.clickOn("#rooms");
        robot.write(ROOMS);
        robot.clickOn("#baths");
        robot.write(BATHS);
        robot.clickOn("#floors");
        robot.write(FLOORS);
        robot.clickOn("#special");
        robot.write(SPECIAL);
        robot.clickOn("#addButton");

        assertThat(robot.lookup("#addhouseMessage").queryText()).hasText(
                String.format("House added successfully!"));

        robot.clickOn("#addButton");
        assertThat(robot.lookup("#addhouseMessage").queryText()).hasText(
                String.format("A house with the address %s already exists!",ADDRESS));



    }
}