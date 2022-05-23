package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.services.BookingService;
import org.loose.fis.sre.services.UserService;


public class BookingController {
    @FXML
    private Text make_bookingMessage;
    @FXML
    private Text agentsMessage;
    @FXML
    private TextField address;
    @FXML
    private TextField special_req;
    @FXML
    private ChoiceBox day;
    @FXML
    private ChoiceBox hour;
    @FXML
    private ChoiceBox month;
    @FXML
    private ChoiceBox year;
    @FXML
    private TextField agent_book;
    @FXML
    private TextField username;

    @FXML
    public void initialize() {
        day.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
                "19","20","21","22","23","24","25","26","27","28","29","30","31");
        month.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
        year.getItems().addAll("2022","2023");
        hour.getItems().addAll("9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00");
    }


    @FXML
    public void handleBookingAction(){
        try
        {
            BookingService.addBooking(address.getText(),(String) day.getValue(),(String) month.getValue(),(String) year.getValue(),(String) hour.getValue(),
                    agent_book.getText(),special_req.getText(),username.getText());
            make_bookingMessage.setText("Booking saved successfully!");
        }
        catch (BookingAlreadyExistsException e) {
            make_bookingMessage.setText(e.getMessage());
       }
        catch (IncorrectNameException e) {
            make_bookingMessage.setText(e.getMessage());
        }
        catch (HouseDoesNotExistsException e) {
            make_bookingMessage.setText(e.getMessage());
        }
        catch (IncorrectDateException e) {
            make_bookingMessage.setText(e.getMessage());
        }
        catch (AgentDoesNotExistException e) {
            make_bookingMessage.setText(e.getMessage());
        }
    }
    @FXML
    public void handleSeeAgents() {
        agentsMessage.setText(UserService.agents_lsit());
    }
}