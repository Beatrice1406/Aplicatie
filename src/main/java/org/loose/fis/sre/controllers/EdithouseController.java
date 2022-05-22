package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.HouseDoesNotExistsException;
import org.loose.fis.sre.services.HouseService;


public class EdithouseController {

    @FXML
    private Text edithouseMessage;
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
    public void handleEditHouseAction(){
        try
        {
            HouseService.editHouse(Address.getText(),Size.getText(),Rooms.getText(),Baths.getText(),Floors.getText(),Special.getText());
            edithouseMessage.setText("Changes saved successfully!");
        }
        catch (HouseDoesNotExistsException e) {
            edithouseMessage.setText(e.getMessage());
        }
    }
    @FXML
    public void handleSearchHouseAction(){
        try
        {

            edithouseMessage.setText(HouseService.searchHouse(Address.getText()));
        }
        catch (HouseDoesNotExistsException e) {
            edithouseMessage.setText(e.getMessage());
        }
    }
}