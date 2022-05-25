package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.BookingService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.HouseService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class SeeHistoryBookingTest {
    public static final String HOUSE = "house";
    public static final String AGENT="Agent";
    /*@BeforeAll
    static void beforeAll() throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        System.out.println("Before Class");
    }*/
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.BOOKING_FOLDER = ".test-see-history_booking";
        FileSystemService.initDirectory_booking();
        FileUtils.cleanDirectory(FileSystemService.getBookingHomeFolder().toFile());
        BookingService.initDatabase();

        FileSystemService.APPLICATION_FOLDER = ".test-see-history_user";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();

        FileSystemService.HOUSE_FOLDER = ".test-see-history_house";
        FileSystemService.initDirectory_house();
        FileUtils.cleanDirectory(FileSystemService.getHouseHomeFolder().toFile());
        HouseService.initDatabase();

        UserService.addUser(AGENT,AGENT,AGENT,AGENT,AGENT);
        HouseService.addHouse(HOUSE,HOUSE,HOUSE,HOUSE,HOUSE,HOUSE);
        BookingService.addBooking(HOUSE,"30","March","2021","10:00",AGENT,"call me",AGENT);
    }
    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("see_historyofbookings.fxml"));
        primaryStage.setTitle("Agentie Imobiliara");
        primaryStage.setScene(new Scene(root, 600, 575));
        primaryStage.show();
    }
    @Test
    void TestSeeHistoryBooking(FxRobot robot) {
        robot.clickOn("#name");
        robot.write(AGENT);
        robot.clickOn("#SeeHistoryButton");
        assertThat(robot.lookup("#seebookingMessage").queryText()).hasText(
                String.format("Booking{address= house\n, day=30, month=March, year= 2021, hour= 10:00\n, agent_book= Agent, special_req= call me\n, accept_booking= not responded, rejection_message=  , Name='Agent}\n"));

        robot.clickOn("#name");
        robot.write("1");
        robot.clickOn("#SeeHistoryButton");
        assertThat(robot.lookup("#seebookingMessage").queryText()).hasText(
                String.format("No bookings for  Agent1!"));
    }
}
