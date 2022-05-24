package org.loose.fis.sre.services;
import javafx.fxml.FXML;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.services.UserService;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Booking;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.model.House;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToBooking;


public class BookingService {

    private static ObjectRepository<Booking> bookingRepository;
    //private static ObjectRepository<House> houseRepository;

    public static void initDatabase() {
        FileSystemService.initDirectory_booking();
        Nitrite database = Nitrite.builder()
                .filePath(getPathToBooking("booking-database.db").toFile())
                .openOrCreate("agent_imb", "agent_imob");

        bookingRepository = database.getRepository(Booking.class);
    }

    public static List<Booking> getAllBookings() {
        return bookingRepository.find().toList();
    }
    public static ObjectRepository<Booking> getBookingRepository()
    {
        return bookingRepository;
    }
    public static void addBooking(String address,String day,String month,String year,String hour, String agent_book, String special_req,String user) throws BookingAlreadyExistsException, IncorrectNameException, HouseDoesNotExistsException, IncorrectDateException, AgentDoesNotExistException
    {
        checkBookingDoesNotAlreadyExist(day,month,year,hour,agent_book);
        UserService.checkAgentDoesExist(agent_book);
        UserService.checkUsername(user);
        HouseService.checkAddressDoesExist(address);
        checkDate(day,month,year);
        bookingRepository.insert(new Booking(address,day,month,year,hour,agent_book,special_req,user));
    }

    static void checkBookingDoesNotAlreadyExist(String day,String month,String year,String hour,String agent_book) throws BookingAlreadyExistsException {
        for (Booking booking : bookingRepository.find()) {
            if (Objects.equals(day, booking.getDay()) && Objects.equals(month, booking.getMonth()) && Objects.equals(year, booking.getYear()) && Objects.equals(hour, booking.getHour())  && Objects.equals(agent_book, booking.getAgent_book()))
                throw new BookingAlreadyExistsException(day,month,year,hour,agent_book);
        }
    }
    private static void checkDate(String day,String month,String year) throws IncorrectDateException {
        if((Objects.equals(day,"30") || Objects.equals(day,"31")) &&(Objects.equals(month,"February")))
        {
            throw new IncorrectDateException(day,month,year);
        }
        if((Objects.equals(day,"31")) && (Objects.equals(month,"April") || Objects.equals(month,"June") || Objects.equals(month,"September") ||  Objects.equals(month,"November")))
        {
            throw new IncorrectDateException(day,month,year);
        }
    }

    public static String  seeBookings(String Name) throws NoBookigsExectpion
    {
        UserService.CheckNameCredentials(Name);
        String s="";
        for (Booking  booking : bookingRepository.find())
        {
            if(Objects.equals(Name, booking.getAgent_book())) {
                s = s + booking.toString();
                s = s + "\n";
            }
        }
        return s;
    }
    public static String  seeHistoryBookings(String Name) throws NoBookigsExectpion
    {
        UserService.CheckNameCredentials(Name);
        String s="";
        for (Booking  booking : bookingRepository.find())
        {
            if(Objects.equals(Name, booking.getUser())) {
                s = s + booking.toString();
                s = s + "\n";
            }
        }
        return s;
    }
    public static void approveBooking(String Name, String hour,  String day,String month, String year) throws IncorrectDateException, AgentDoesNotExistException,BookingNotFoundException
    {
        UserService.checkAgentDoesExist(Name);
        checkDate(day,month,year);
        for (Booking  booking : bookingRepository.find())
        {
            if(Objects.equals(Name, booking.getAgent_book()) && Objects.equals(day, booking.getDay()) && Objects.equals(month, booking.getMonth()) && Objects.equals(year, booking.getYear()) && Objects.equals(hour, booking.getHour() ))

            {
                booking.setAccept_booking("approved");
                booking.setRejection_message("");
                bookingRepository.update(booking);
                return;
            }
        }
        throw new BookingNotFoundException(Name, hour, day, month, year);
    }
    public static void rejectBooking(String Name, String hour,  String day,String month, String year, String Reason) throws IncorrectDateException, AgentDoesNotExistException, BookingNotFoundException
    {
        UserService.checkAgentDoesExist(Name);
        checkDate(day,month,year);
        for (Booking  booking : bookingRepository.find())
        {
            if(Objects.equals(Name, booking.getAgent_book()) && Objects.equals(day, booking.getDay()) && Objects.equals(month, booking.getMonth()) && Objects.equals(year, booking.getYear()) && Objects.equals(hour, booking.getHour() ))
            {
                booking.setAccept_booking("rejectet");
                booking.setRejection_message(Reason);
                bookingRepository.update(booking);
                return;
            }

        }
        throw new BookingNotFoundException(Name, hour, day, month, year);

    }

}