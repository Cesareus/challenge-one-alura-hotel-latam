package ar.com.alura.hotel.tests;

import java.sql.Connection;
import java.sql.SQLException;

import ar.com.alura.hotel.factory.ConnectionFactory;

public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.recuperaConexion();

        System.out.println("Cerrando la conexión");

        con.close();
    }

}
