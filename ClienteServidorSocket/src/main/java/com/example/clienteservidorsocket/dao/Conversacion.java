package com.example.clienteservidorsocket.dao;


import com.example.clienteservidorsocket.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Conversacion implements ConversacionDAO{
    Conexion instanciaMySQL = Conexion.getInstance();

    @Override
    public void registrarConversacionA(String mensaje) {
        PreparedStatement consulta = null;
        Connection conexion = null;

        try {
            conexion = instanciaMySQL.conectar();
            consulta = conexion.prepareStatement("insert into conversaciones(conversacionA) values(?)");

            consulta.setString(1, mensaje);
            consulta.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    @Override
    public void registrarConversacionB(String mensaje) {
        PreparedStatement consulta = null;
        Connection conexion = null;

        try {
            conexion = instanciaMySQL.conectar();
            consulta = conexion.prepareStatement("insert into conversaciones(conversacionB) values(?)");

            consulta.setString(1, mensaje);
            consulta.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }
    @Override
    public void registrarConversacionC(String mensaje) {
        PreparedStatement consulta = null;
        Connection conexion = null;

        try {
            conexion = instanciaMySQL.conectar();
            consulta = conexion.prepareStatement("insert into conversaciones(conversacionC) values(?)");

            consulta.setString(1, mensaje);
            consulta.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }



}
