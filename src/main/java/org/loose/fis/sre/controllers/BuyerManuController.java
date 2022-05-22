package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.services.HouseService;

import java.io.IOException;

public class BuyerManuController {


    @FXML
    public void SeeHouses() {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("see_houses.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Agentie Imobiliara");
            stage.setScene(new Scene(root, 600, 575));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}