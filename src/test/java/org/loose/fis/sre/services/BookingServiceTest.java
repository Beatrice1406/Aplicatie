package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.Booking;
import org.loose.fis.sre.model.House;
import org.loose.fis.sre.model.User;

import java.io.IOException;
import java.nio.file.FileSystemLoopException;

import static org.assertj.core.api.InstanceOfAssertFactories.BYTE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingServiceTest {
    public static final String ADMIN = "admin";
    public static final String AGENT = "Agent";
    public static final String ADDRESS="Address";
    public static final String SIZE="Size";
    public static final String ROOMS="Rooms";
    public static final String BATHS="Baths";
    public static final String FLOORS="Floors";
    public static final String SPECIAL="Special";

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

    @BeforeAll
    static void beforeAll() throws Exception {
        FileSystemService.BOOKING_FOLDER = ".test-booking";
        FileSystemService.initDirectory_booking();
        FileUtils.cleanDirectory(FileSystemService.getBookingHomeFolder().toFile());
        BookingService.initDatabase();

        FileSystemService.APPLICATION_FOLDER = ".test-registration-forbooking";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        System.out.println("Before class");

        FileSystemService.HOUSE_FOLDER = ".test-addhouses-forbooking";
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
    @Order(11)
    @DisplayName("Booking is approved")
    void testApproveBooking() throws IncorrectDateException, AgentDoesNotExistException, BookingNotFoundException {
        BookingService.approveBooking(AGENT, ADMIN, ADMIN, ADMIN, ADMIN);
        Booking booking = BookingService.getAllBookings().get(0);
        assertThat(booking.getAccept_booking()).isEqualTo("approved");
        System.out.println("11");

    }

    @Test
    @Order(12)
    @DisplayName("Booking date for approving is incorrect")
    void testApproveBookingNotDate() throws AgentDoesNotExistException, BookingNotFoundException {
        assertThrows(IncorrectDateException.class, () -> {
            BookingService.approveBooking(AGENT, "20", "31", "February", "2020");
        });
        System.out.println("12");
    }

    @Test
    @Order(13)
    @DisplayName("Booking agent for approving is incorrect")
    void testApproveBookingNotAgent() throws IncorrectDateException, BookingNotFoundException {
        assertThrows(AgentDoesNotExistException.class, () -> {
            BookingService.approveBooking("notAnAgent", "20", "27", "February", "2020");
        });
        System.out.println("13");
    }

    @Test
    @Order(14)
    @DisplayName("Booking for approving is not found")
    void testApproveBookingNotBooking() throws IncorrectDateException, AgentDoesNotExistException {

        assertThrows(BookingNotFoundException.class, () -> {
            BookingService.approveBooking(AGENT, "20", "27", "February", "2020");
        });
        System.out.println("14");
    }

    @Test
    @Order(17)
    @DisplayName("Booking rejected succefully")
    void testRejectBooking() throws IncorrectDateException, AgentDoesNotExistException, BookingNotFoundException
    {
        BookingService.rejectBooking(AGENT, ADMIN, ADMIN, ADMIN, ADMIN, "onereason");
        Booking booking = BookingService.getAllBookings().get(0);
        assertThat(booking.getAccept_booking()).isEqualTo("rejectet");
        System.out.println("17");
    }
    @Test
    @Order(18)
    @DisplayName("Booking date for rejecting is incorrect")
    void testRejectBookingNotDate() throws AgentDoesNotExistException, BookingNotFoundException {
        assertThrows(IncorrectDateException.class, () -> {
            BookingService.rejectBooking(AGENT, "20", "31", "February", "2020", "onereason");
        });
        System.out.println("18");
    }
    @Test
    @Order(19)
    @DisplayName("Booking agent for rejecting is incorrect")
    void testRejectBookingNotAgent() throws IncorrectDateException, BookingNotFoundException {
        assertThrows(AgentDoesNotExistException.class, () -> {
            BookingService.rejectBooking("notAnAgent", "20", "27", "February", "2020", "thereason");
        });
        System.out.println("19");
    }
    @Test
    @Order(20)
    @DisplayName("Booking for rejecting is not found")
    void testRejectBookingNotBooking() throws IncorrectDateException, AgentDoesNotExistException {

        assertThrows(BookingNotFoundException.class, () -> {
            BookingService.rejectBooking(AGENT, "20", "27", "February", "2020", "one reason");
        });
        System.out.println("20");
    }


}

