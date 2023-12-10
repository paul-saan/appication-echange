package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML 
    private void TeenagerList(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TeenagerList.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();     
    }

     @FXML 
     private void Parameters(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("Parameters.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }

     @FXML 
     private void CoupleTeenagers(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("CoupleTeenager.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }

    @FXML 
    private void AvoidMakeCoupleTeenager(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("AMteens.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }

     @FXML 
    private void Import(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("Import.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }

     @FXML 
     private void Export(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("Exporrt.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
}
