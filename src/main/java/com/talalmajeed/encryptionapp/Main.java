package com.talalmajeed.encryptionapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        // Retrieve the controller after loading the FXML
        MainController controller = fxmlLoader.getController();
        controller.initialize();

        stage.setTitle("Encryption App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
