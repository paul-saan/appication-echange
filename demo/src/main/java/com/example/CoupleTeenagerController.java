package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CoupleTeenagerController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<CoupleTeen> listView ;

    @FXML 
    Label country1; 

    @FXML 
    Label country2; 

    @FXML 
    Label name1; 

    @FXML 
    Label name2;
    
    @FXML 
    Label firstname1; 

    @FXML 
    Label firstname2; 

    @FXML 
    Label numero1; 

    @FXML 
    Label numero2; 


    @FXML
    private void initialize(){
        String filename = "affectations.csv";
        ArrayList<CoupleTeen> couples = new ArrayList<CoupleTeen>();
        try{
            BufferedReader br= new BufferedReader(new FileReader(new File(filename)));
            String currentLine = br.readLine();
            while(currentLine!=null){
                currentLine=br.readLine();
                String[] tab = currentLine.split(";");
                couples.add(new CoupleTeen(tab[1], tab[5], tab[3], tab[7],tab[2],tab[6],tab[0],tab[4]));

            }
        }catch(Exception e){
            e.getStackTrace();
        } 

    
        
        listView.getItems().setAll(couples);
        listView.setCellFactory(list-> new ViewCell());
        listView.getSelectionModel().selectedIndexProperty().addListener((observable,oldValue,newValue)->{
            if(newValue.intValue()>=0){
                CoupleTeen selected = listView.getSelectionModel().getSelectedItem();
                country1.setText(selected.getCountry1());
                country2.setText(selected.getCountry2());
                firstname1.setText(selected.getFirstname1());
                firstname2.setText(selected.getFirstname2());
                name1.setText(selected.getName1());
                name2.setText(selected.getName2());
                numero1.setText(selected.getID1());
                numero2.setText(selected.getID2());
            }
        });
        
    }

    public class ViewCell extends ListCell<CoupleTeen>{
        protected void updateItem(CoupleTeen item,boolean empty){
            super.updateItem(item, empty);
            if(empty||item==null){
                setText(null);
                setGraphic(null);
            }else{
                setText(item.getFirstname1()+" and "+item.getFirstname2());
            }
        }
    }
  
    

     @FXML 
     private void BackParameters(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
}
