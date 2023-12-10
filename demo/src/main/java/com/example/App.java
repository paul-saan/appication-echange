package com.example;






import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
public void start(Stage stage) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                java.net.URL fxmlFileUrl = getClass().getResource("HomePage.fxml");
                if (fxmlFileUrl == null) {
                        System.out.println("Impossible de charger le fichier fxml");
                        System.exit(-1);
                }
                loader.setLocation(fxmlFileUrl);
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Plateforme d'affectation");
                stage.show();
        }

        public static void main(String[] args) {
                Application.launch(args);
        }
}