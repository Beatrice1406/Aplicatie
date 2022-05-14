package org.loose.fis.sre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {


        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("open_app.fxml"));
        primaryStage.setTitle("Agentie imobiliara");
        primaryStage.setScene(new Scene(root,600, 575));
        primaryStage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }
}
