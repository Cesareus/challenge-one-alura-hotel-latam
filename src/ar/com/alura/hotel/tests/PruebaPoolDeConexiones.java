package ar.com.alura.hotel.tests;

import java.sql.SQLException;

import ar.com.alura.hotel.factory.ConnectionFactory;

public class PruebaPoolDeConexiones {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        
        for (int i = 0; i < 20; i++) {
            factory.recuperaConexion();
            
            System.out.println("Abriendo conexión #" + i);
        }
    }
    
}
