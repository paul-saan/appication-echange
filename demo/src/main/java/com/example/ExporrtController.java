package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExporrtController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML 
     private void BackExporrt(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
}
