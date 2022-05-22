package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class EditHouse {

    @FXML
    private Text addhouseMessage;
    @FXML
    private TextField Address;
    @FXML
    private TextField  Size;
    @FXML
    private TextField  Rooms;
    @FXML
    private TextField  Baths;
    @FXML
    private TextField  Floors;
    @FXML
    private TextField  Special;



    @FXML
    public void handleAddHouse() {


        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("addhouse.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Agentie Imobiliara");
            stage.setScene(new Scene(root, 600, 575));
            stage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    public void handleEditHouse() {


        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("edithouse.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Agentie Imobiliara");
            stage.setScene(new Scene(root, 600, 575));
            stage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    public void handleDeleteHouse() {


        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("deletehouse.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Agentie Imobiliara");
            stage.setScene(new Scene(root, 600, 575));
            stage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    public void handleSeeBookings() {


        try {
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("see_bookings.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Agentie Imobiliara");
            stage.setScene(new Scene(root, 600, 575));
            stage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
}

