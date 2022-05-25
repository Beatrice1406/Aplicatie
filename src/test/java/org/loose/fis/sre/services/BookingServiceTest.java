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
/*@BeforeEach
    void setUp() throws Exception {
        FileSystemService.BOOKING_FOLDER = ".test-booking";
        FileSystemService.initDirectory_booking();
        FileUtils.cleanDirectory(FileSystemService.getBookingHomeFolder().toFile());
        BookingService.initDatabase();
        System.out.println("Before each");
    }*/

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
    @Order(1)
    @DisplayName("Database is initialized and there are no bookings")
    void testDataBaseIsInitializedAndNoBookingIsPersisted() {
        assertThat(BookingService.getAllBookings()).isNotNull();
        assertThat(BookingService.getAllBookings()).isEmpty();
        System.out.println("1");
    }

    @Test
    @Order(2)
    @DisplayName("Booking can not be added for an agent that does not exist")
    void testBookingForWrongAgent() {
        assertThrows(AgentDoesNotExistException.class, () -> {
            BookingService.addBooking(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN);
        });
        System.out.println("2");
    }

    @Test
    @Order(3)
    @DisplayName("Booking can not be added for a name that does not exist")
    void testBookingForWrongName() {
        assertThrows(IncorrectNameException.class, () -> {
            UserService.addUser(AGENT, AGENT, AGENT, AGENT, AGENT);
            BookingService.addBooking(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, AGENT, ADMIN, ADMIN);
        });
        System.out.println("3");
    }

    @Test
    @Order(4)
    @DisplayName("Booking can not be added for an address that does not exist")
    void testBookingForWrongAddress() {
        assertThrows(HouseDoesNotExistsException.class, () -> {
            HouseService.addHouse(ADDRESS, SIZE, ROOMS,BATHS,FLOORS, SPECIAL);
            BookingService.addBooking(ADMIN,ADMIN,ADMIN,ADMIN,ADMIN,AGENT,ADMIN,AGENT);
        });
        System.out.println("4");
    }
    @Test
    @Order(5)
    @DisplayName("Booking can not be added for a date that does not exist")
    void testBookingForWrongDate() {
        assertThrows(IncorrectDateException.class, () -> {
            BookingService.addBooking(ADDRESS,"31","February",ADMIN,ADMIN,AGENT,ADMIN,AGENT);
        });
        System.out.println("5");
    }
    @Test
    @Order(6)
    @DisplayName("Booking is added to database")
    void testBookingIsAddedToDatabase() throws IncorrectDateException, AgentDoesNotExistException, IncorrectNameException, BookingAlreadyExistsException, HouseDoesNotExistsException {
        BookingService.addBooking(ADDRESS,ADMIN,ADMIN,ADMIN,ADMIN,AGENT,ADMIN,AGENT);
        assertThat(BookingService.getAllBookings()).isNotEmpty();
        assertThat(BookingService.getAllBookings()).size().isEqualTo(1);
        Booking booking = BookingService.getAllBookings().get(0);
        org.assertj.core.api.Assertions.assertThat(booking).isNotNull();
        org.assertj.core.api.Assertions.assertThat(booking.getAddress()).isEqualTo(ADDRESS);
        org.assertj.core.api.Assertions.assertThat(booking.getDay()).isEqualTo(ADMIN);
        org.assertj.core.api.Assertions.assertThat(booking.getMonth()).isEqualTo(ADMIN);
        org.assertj.core.api.Assertions.assertThat(booking.getYear()).isEqualTo(ADMIN);
        org.assertj.core.api.Assertions.assertThat(booking.getHour()).isEqualTo(ADMIN);
        org.assertj.core.api.Assertions.assertThat(booking.getAgent_book()).isEqualTo(AGENT);
        org.assertj.core.api.Assertions.assertThat(booking.getSpecial_req()).isEqualTo(ADMIN);
        org.assertj.core.api.Assertions.assertThat(booking.getUser()).isEqualTo(AGENT);
        System.out.println("6");
    }
    @Test
    @Order(7)
    @DisplayName("Booking can not be added twice")
    void testBookingCanNotBeAddedTwice() {
        assertThrows(BookingAlreadyExistsException.class, () -> {
            BookingService.addBooking(ADMIN,ADMIN,ADMIN,ADMIN,ADMIN,AGENT,ADMIN,ADMIN);
            BookingService.addBooking(ADMIN,ADMIN,ADMIN,ADMIN,ADMIN,AGENT,ADMIN,ADMIN);
        });
        System.out.println("7");
    }

    @Test
    @Order(8)
    @DisplayName("Booking list is corect")
    void testSeeBookings() throws NoBookigsExectpion {
        assertThat(BookingService.seeBookings(AGENT)).isEqualTo("Booking{address= Address\n, day=admin, month=admin, year= admin, hour= admin\n, agent_book= Agent, special_req= admin\n, accept_booking= not responded, rejection_message=  , Name='Agent}\n");
        System.out.println("8");
    }

    @Test
    @Order(9)
    @DisplayName("Booking list is empty")
    void testSeeBookingsEmpty() {
        assertThrows(NoBookigsExectpion.class, () -> {
                    BookingService.seeBookings("notAgent");
                }
        );
        System.out.println("9");
    }
    @Test
    @Order(10)
    @DisplayName("Booking does not already exist")
    void testBookingDesNotAlreadyExist() {
        assertThrows(BookingAlreadyExistsException.class, () -> {
                    BookingService.addBooking(ADMIN,ADMIN,ADMIN,ADMIN,ADMIN,AGENT,ADMIN,ADMIN);
                    BookingService.checkBookingDoesNotAlreadyExist(ADMIN,ADMIN,ADMIN,ADMIN,AGENT);
                }
        );
        System.out.println("10");
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
    @Order(15)
    @DisplayName("Date is not correct ")
    void testCheckDate() {
        assertThrows(IncorrectDateException.class, () -> {
                    BookingService.addBooking(ADDRESS,"31","June",ADMIN,ADMIN,AGENT,ADMIN,AGENT);
                }
        );
        System.out.println("15");
    }
    @Test
    @Order(16)
    @DisplayName("History of bookings is correct")
    void testSeeBookingHistory() throws NoBookigsExectpion {
        assertThat(BookingService.seeHistoryBookings(AGENT)).isEqualTo("Booking{address= Address\n, day=admin, month=admin, year= admin, hour= admin\n, agent_book= Agent, special_req= admin\n, accept_booking= approved, rejection_message= , Name='Agent}\n");
        System.out.println("16");

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

