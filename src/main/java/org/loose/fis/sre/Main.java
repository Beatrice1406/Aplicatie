package org.loose.fis.sre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.sre.services.HouseService;
import org.loose.fis.sre.services.UserService;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        UserService.initDatabase();
        HouseService.initDatabase();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("open_app.fxml"));
        primaryStage.setTitle("Agentie imobiliara");
        primaryStage.setScene(new Scene(root,600, 575));
        primaryStage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }
}
