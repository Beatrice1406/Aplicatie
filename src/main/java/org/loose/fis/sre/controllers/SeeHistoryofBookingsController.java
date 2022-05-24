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
public class SeeHistoryofBookingsController {

    @FXML
    private Text seebooksmesage;
    @FXML
    private TextField Name;


    @FXML
    public void handleSeeHystoryofBookings() {
        try {

            seebooksmesage.setText(BookingService.seeHistoryBookings(Name.getText()));
        } catch (NoBookigsExectpion e) {
            seebooksmesage.setText(e.getMessage());
        }

    }
}



