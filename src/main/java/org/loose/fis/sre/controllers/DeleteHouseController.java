package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.HouseDoesNotExistsException;
import org.loose.fis.sre.services.HouseService;


public class DeleteHouseController {

    @FXML
    private Text deletehouseMessage;
    @FXML
    private TextField Address;


    @FXML
    public void handleDeleteHouseAction(){
        try
        {
            HouseService.deleteHouse(Address.getText());
            deletehouseMessage.setText("House  successfully deleted!");
        }
        catch (HouseDoesNotExistsException e) {
            deletehouseMessage.setText(e.getMessage());
        }
    }
    @FXML
    public void handleSearchHouseAction(){
        try
        {

            deletehouseMessage.setText(HouseService.searchHouse(Address.getText()));
        }
        catch (HouseDoesNotExistsException e) {
            deletehouseMessage.setText(e.getMessage());
        }
    }
}