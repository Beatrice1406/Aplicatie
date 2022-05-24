package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.NoBookigsExectpion;
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



