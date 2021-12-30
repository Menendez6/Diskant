package main.java.isw21.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.java.isw21.configuration.PropertiesISW;
/**
 * Clase encargado de establecer la conexion del servidor con la base datos para las llamadas. Patrón Singleton.
 * @version 0.1
 */

public class ConnectionDAO {
    private static ConnectionDAO connectionDAO;
    private Connection con;

    private ConnectionDAO() {
        String url = PropertiesISW.getInstance().getProperty("ddbb.connection");
        String user = PropertiesISW.getInstance().getProperty("ddbb.user");
        String password = PropertiesISW.getInstance().getProperty("ddbb.password");
        try {
            con = DriverManager.getConnection(url, user, password);
        }catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

    }

    /**
     * Método utilizado para aplicar el patrón Singleton, es decir que tengamos una sola instancia.
     * @return un connectionDAO nuevo si es la primera vez que se crea en el código. Si ya está creado previamente, se devuelve el objeto ya creado.
     * De esta forma consigues que solo haya una instancia.
     */
    public static ConnectionDAO getInstance() {
        if (connectionDAO == null) {
            connectionDAO=new ConnectionDAO();
        }
        return connectionDAO;
    }

    public Connection getConnection() {
        return con;
    }

    /*public static void main(String[] args) {
        String url = PropertiesISW.getInstance().getProperty("ddbb.connection");
        String user = PropertiesISW.getInstance().getProperty("ddbb.user");
        String password = PropertiesISW.getInstance().getProperty("ddbb.password");
        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios");
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {

                System.out.print(rs.getString(1));
                System.out.print(": ");
                System.out.println(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
}