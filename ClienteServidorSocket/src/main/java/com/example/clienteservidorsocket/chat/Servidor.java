package com.example.clienteservidorsocket.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class Servidor extends Observable implements Runnable {

    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        ServerSocket servidor = null;
        Socket socket = null;
        DataInputStream entrada; //Sirve para recibir los datas

        try {
            //Creamos el servidor del socket
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");

            //Siempre estará escuchando peticiones
            while (true) {
                //Espero que el cliente se conecte
                socket = servidor.accept(); //El servidor acepta peticion del cliente
                System.out.println("Cliente conectado");
                entrada = new DataInputStream(socket.getInputStream());

                //Leemos el mensaje
                String mensaje = entrada.readUTF();
                System.out.println(mensaje);

                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();

                socket.close();
                System.out.println("Cliente desconectado");
            }
        } catch (IOException error) {
            System.out.println(error);
        }
    }

}
