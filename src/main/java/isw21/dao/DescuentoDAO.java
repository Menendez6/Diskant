package main.java.isw21.dao;
import main.java.isw21.descuentos.Descuento;
import main.java.isw21.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DescuentoDAO {
    public static Descuento addDescuento(Customer customer, Descuento descuento) {
        // iniciamos la conexion con la base de datos
        Connection con = ConnectionDAO.getInstance().getConnection();
        try {
            // Añadimos el descuento a la tabla de descuentos con el formato previamente establecido.
            // El primer valor será el ID del dueño del descuento seguido por el descuento.
            PreparedStatement pst = con.prepareStatement("INSERT INTO descuentos VALUES ('" + customer.getId() + "','" + descuento.getComercio() + "','" + descuento.getFechaIn() + "','"
                    + descuento.getFechaFin() + "','" + descuento.getTipo() + "','" + descuento.getValor() + "','" + descuento.getCodigo() + "');");
            ResultSet rs = pst.executeQuery();
            return descuento;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static ArrayList<Descuento> getDescuentos(ArrayList<Descuento> lista, Customer customer){
        //Como debemos extraer descuentos, necesitamos conexion a la base de datos, por lo que tenemos que gnerar una conexion
        Connection con = ConnectionDAO.getInstance().getConnection();
        //Si la lista que debemos actualizar no existe o si su tamaño es cero, la volvemos a crear
        if(lista == null || lista.size() == 0)
        {
            lista = new ArrayList<Descuento>();
        }
        //Ejecutamos la query que obtiene los descuentos asociados a un usuario
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM descuentos WHERE usuario= '"+customer.getId()+ "';");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                // Estos descuentos son añadidos a la lista que se ha pasado por parámetro.
                lista.add(new Descuento(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }


}
