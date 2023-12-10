package com.example;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TeenagerListController {

    
    @FXML
    ListView<Teenager> listView ;

    @FXML
    Label firstname ;

    @FXML
    Label name ;

    @FXML
    Label country ;

    @FXML
    Label birth;

    @FXML
    Label gender ;

  
   
    


    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private void initialize(){
        ArrayList<Teenager> test = Plateform.importTeenagerFromCSV("/home/infoetu/matys.lepretre.etu/S2/F3/demo/src/main/java/com/example/adosAleatoires.csv");
       
        listView.getItems().setAll(test);
        listView.setCellFactory(list-> new ViewCell());

         listView.getSelectionModel().selectedIndexProperty().addListener((observable,oldValue,newValue)->{
            if(newValue.intValue()>=0){
                Teenager selected = listView.getSelectionModel().getSelectedItem();
                firstname.setText(selected.getFirstName());
                name.setText(selected.getName());
                country.setText(selected.getCountry().toString());
                birth.setText(selected.getDATE_OF_BIRTH()+" ");
                
               
            }
        });
        
    }

    public class ViewCell extends ListCell<Teenager>{
        protected void updateItem(Teenager item,boolean empty){
            super.updateItem(item, empty);
            if(empty||item==null){
                setText(null);
                setGraphic(null);
            }else{
                setText(item.getFirstName()+" "+item.getName());
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
