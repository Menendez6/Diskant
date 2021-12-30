package main.java.isw21.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.isw21.controler.CustomerControler;
import main.java.isw21.domain.Customer;

/**
 * Clase encargada de ejecutar los metodos asociados a los customers
 * @version 0.3
 */
public class CustomerDAO {

    /**
     * Llama a la base de datos para obtener un cliente
     * @param lista lista a la que se van a añadir los clientes
     * @return la lista que se le pasa por parámetro para el test
     */
    public static ArrayList<Customer> getClientes(ArrayList<Customer> lista) {
        Connection con=ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                lista.add(new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5),rs.getDouble(6),rs.getInt(7)));
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return  lista;
    }

    /**
     * LLama a la base de datos para añadir un cliente a la base de datos
     * @param customer el cliente a añadir
     * @return
     */
    public static Customer addCliente(Customer customer){
        //Deberemos comprobar que el cliente a añadir no se encuentra en la base de datos
        if (CustomerDAO.isInBase(customer)==null){
            //Sabiendo que no figura en la base, ejecutamos la QUERY necesaria para añadir el usuario a la tabla de usuarios
            Connection con= ConnectionDAO.getInstance().getConnection();
            try{
                //La query introducira el nuevo usuario y su contraseña
                PreparedStatement pst = con.prepareStatement("INSERT INTO usuarios VALUES ('"+customer.getUsuario()+"','"+customer.getContraseña()+"','"+customer.getEmail()+"','"+0+"','"+0+"','"+0+"','"+0+"');");
                ResultSet rs = pst.executeQuery();
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
            return customer;
        }
        else{
            System.out.println("El usuario ya se encuentra dentro de la base de datos.");
            return null;
        }
    }

    /**
     * Llama a la base de datos para comprobar si el usuario está en la base de datos
     * @param customerIN el customer que queremos ver si está en la base de datos
     * @return El customer si está en la base y null si no está
     */
    public static Customer isInBase(Customer customerIN){
        CustomerControler customerControler=new CustomerControler();
        ArrayList<Customer> listaCust=new ArrayList<Customer>();
        //extraemos la lista de customers y vemos si el introducido figura en ella
        customerControler.getCustomer(listaCust);
        for (Customer customer : listaCust) {
            System.out.println("El usuario: "+customer.getUsuario()+" tiene una contraseña asociada: "+customer.getContraseña());
            if (customerIN.equals(customer)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Llama a la base de datos para actualizar la cantidad ahorrada en descuentos del usuario
     * @param customer el cliente a quien pertence el descuento gastado
     * @param valor el valor del descuento
     */
    public static void updateDescuento(Customer customer, double valor){
        Connection con=ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("UPDATE usuarios SET descuento = " + valor + " WHERE usuario = '" + customer.getUsuario()  + "';");
             ResultSet rs = pst.executeQuery()) {

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }


    }

    /*public static void getDatosCLiente(ArrayList<Customer> lista, Customer customer) {
        Connection con=ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios WHERE id="+customer.getId());
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                lista.add(new Customer(rs.getString(1),rs.getString(2)));
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }*/

    public static void main(String[] args) {


        ArrayList<Customer> lista=new ArrayList<Customer>();
        CustomerDAO.getClientes(lista);


        for (Customer customer : lista) {
            System.out.println("He leído el usuario: "+customer.getUsuario()+" con contraseña: "+customer.getContraseña());
        }


    }

    /**
     * Llama a la base de datos para actualizar la cantidad ahorrada en cheques del usuario
     * @param customer el cliente a quien pertence el cheque gastado
     * @param valorCheque el valor de la cantidad gastada del cheque
     */
    public static void updateCheque(Customer customer, double valorCheque) {
        Connection con=ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("UPDATE usuarios SET regalo = " + valorCheque + " WHERE usuario = '" + customer.getUsuario()  + "';");
             ResultSet rs = pst.executeQuery()) {

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }

    /**
     * Llama a la base de datos para actualizar la cantidad ahorrada en porcentajes del usuario
     * @param customer el cliente a quien pertence el porcentaje gastado
     * @param valorPorcentaje el valor del porcentaje
     * @param numPorcentajes el número de porcentajes usados (para hacer la media)
     */
    public static void updatePorcentaje(Customer customer, double valorPorcentaje, int numPorcentajes) {
        Connection con=ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("UPDATE usuarios SET porcentaje = " + valorPorcentaje + ", numperc = " + numPorcentajes +" WHERE usuario = '" + customer.getUsuario()  + "';");
             ResultSet rs = pst.executeQuery()) {

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }
}