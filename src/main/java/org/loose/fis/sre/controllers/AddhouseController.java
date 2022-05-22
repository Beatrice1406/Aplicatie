package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.AddressAlreadyExistsException;
import org.loose.fis.sre.services.HouseService;


public class AddhouseController {

    @FXML
    private Text addhouseMessage;
    @FXML
    private TextField Address;
    @FXML
    private TextField Size;
    @FXML
    private TextField Rooms;
    @FXML
    private TextField Baths;
    @FXML
    private TextField Floors;
    @FXML
    private TextField Special;


    @FXML
    public void handleAddHouseAction(){
        try
        {
            HouseService.addHouse(Address.getText(),Size.getText(),Rooms.getText(),Baths.getText(),Floors.getText(),Special.getText());
            addhouseMessage.setText("House added successfully!");
        }
     catch (AddressAlreadyExistsException e) {
        addhouseMessage.setText(e.getMessage());
    }
    }
}