package com.example.clienteservidorsocket.test;

import com.example.clienteservidorsocket.chat.Servidor;
import com.example.clienteservidorsocket.chat.Cliente;
import com.example.clienteservidorsocket.dao.Conversacion;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class ControllerB implements Initializable, Observer {

    @FXML
    private AnchorPane ap_main;

    @FXML
    private Button btLimpiar;

    @FXML
    private Button btnEnviar;

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtMensaje;

    @FXML
    private TextField txtNombre;


    Conversacion conversacion = new Conversacion();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
// Inicializa y arranca el servidor
        Servidor servidor = new Servidor(6000);
        servidor.addObserver(this);
        Thread hilo = new Thread(servidor);
        hilo.start();


    }

    @FXML
    void enviar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String mensaje = txtMensaje.getText();
        String mensajeAEnviar = nombre + ": " + mensaje + "\n";

        if(nombre.isEmpty() || mensaje.isEmpty() || mensajeAEnviar.isEmpty()){
            AlertUtils.mostrarError("Todos los campos deben estar completos");
        }else{
            // Agrega el mensaje al TextArea
            txtArea.appendText(mensajeAEnviar);
            //Agrego el mensaje a la base de datos
            conversacion.registrarConversacionB(mensajeAEnviar);

            // Envía el mensaje al servidor
            Cliente cliente = new Cliente(5000, mensajeAEnviar);
            Thread hilo = new Thread(cliente);
            hilo.start();
            Cliente cliente2 = new Cliente(7000, mensajeAEnviar);
            Thread hilo2 = new Thread(cliente2);
            hilo2.start();

            txtMensaje.setText("");
        }



    }

    @FXML
    void limpiar(ActionEvent event) {
        txtNombre.clear();
        txtMensaje.clear();
        txtArea.clear();
    }

    @Override
    public void update(Observable o, Object arg) {
        // Actualiza la interfaz de usuario con el mensaje recibido del servidor
        String mensajeRecibido = (String) arg;
        Platform.runLater(() -> {
            // Agrega el mensaje al TextArea
            txtArea.appendText(mensajeRecibido);
        });

    }


}
