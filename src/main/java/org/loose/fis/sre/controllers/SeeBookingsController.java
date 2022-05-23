package org.loose.fis.sre.controllers;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.Booking;
import org.loose.fis.sre.controllers.LoginController;

import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.services.BookingService;
public class SeeBookingsController {

    @FXML
    private Text editbooking;
    @FXML
    private Text seebooksmesage;
    @FXML
    private TextField Name;

    @FXML
    private ChoiceBox Day;
    @FXML
    private ChoiceBox Hour;
    @FXML
    private ChoiceBox Month;
    @FXML
    private ChoiceBox Year;
    @FXML
    private TextField Reason;


    @FXML
    public void initialize() {
        Day.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
                "19","20","21","22","23","24","25","26","27","28","29","30","31");
        Month.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
        Year.getItems().addAll("2021","2022");
        Hour.getItems().addAll("9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00");
    }


    @FXML
    public void handleSeeBookings() {
        try {

            seebooksmesage.setText(BookingService.seeBookings(Name.getText()));
        } catch (NoBookigsExectpion e) {
            seebooksmesage.setText(e.getMessage());
        }

    }
    public void handleApprove()
    {
        try{
            BookingService.approveBooking(Name.getText(),(String) Hour.getValue(),(String) Day.getValue(),(String) Month.getValue(),(String) Year.getValue());
            editbooking.setText("Booking approved!");
        }
        catch (IncorrectDateException e) {
            editbooking.setText(e.getMessage());
        }
        catch (AgentDoesNotExistException e) {
            editbooking.setText(e.getMessage());
        }
        catch (BookingNotFoundException e) {
            editbooking.setText(e.getMessage());
        }

    }
    public void handleReject()
    {
        try{
            BookingService.rejectBooking(Name.getText(),(String) Hour.getValue(),(String) Day.getValue(),(String) Month.getValue(),(String) Year.getValue(), Reason.getText());
            editbooking.setText("Booking rejected!");
        }
        catch (IncorrectDateException e) {
            editbooking.setText(e.getMessage());
        }
        catch (AgentDoesNotExistException e) {
            editbooking.setText(e.getMessage());
        }
        catch (BookingNotFoundException e) {
            editbooking.setText(e.getMessage());
        }

    }

}



