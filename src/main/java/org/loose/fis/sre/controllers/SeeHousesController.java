package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.HouseDoesNotExistsException;
import org.loose.fis.sre.services.HouseService;

import java.io.IOException;


public class SeeHousesController {

    @FXML
    private Text seehousemesage;
    @FXML
    private TextField Address;


    @FXML
    public void handleSeeHouses() {
        seehousemesage.setText(HouseService.seeHouses());
    }

    @FXML
    public void handleSearchHouseAction(){
        try
        {
            seehousemesage.setText(HouseService.searchHouse(Address.getText()));
        }
        catch (HouseDoesNotExistsException e) {
            seehousemesage.setText(e.getMessage());
        }
    }
    @FXML
    public void handlebook() {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("book.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Agentie Imobiliara");
            stage.setScene(new Scene(root, 600, 575));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}