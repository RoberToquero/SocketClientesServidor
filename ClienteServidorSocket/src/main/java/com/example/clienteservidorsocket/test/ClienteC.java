package com.example.clienteservidorsocket.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class ClienteC extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteC.class.getResource("/com/example/clienteservidorsocket/conversacionC.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Cliente C");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar el archivo FXML");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

