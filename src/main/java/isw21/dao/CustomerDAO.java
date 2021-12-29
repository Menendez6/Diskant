package main.java.isw21.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.isw21.controler.CustomerControler;
import main.java.isw21.domain.Customer;
import main.java.isw21.dao.ConnectionDAO;
// Clase encargada de ejecutar los metodos asociados a los customers, como getClientes
public class CustomerDAO {

    public static ArrayList<Customer> getClientes(ArrayList<Customer> lista) {
        Connection con=ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                lista.add(new Customer(rs.getString(1),rs.getString(2)));
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return  lista;
    }

    public static Customer addCliente(Customer customer){
        //Deberemos comprobar que el cliente a añadir no se encuentra en la base de datos
        if (CustomerDAO.isInBase(customer)==null){
            //Sabiendo que no figura en la base, ejecutamos la QUERY necesaria para añadir el usuario a la tabla de usuarios
            Connection con= ConnectionDAO.getInstance().getConnection();
            try{
                //La query introducira el nuevo usuario y su contraseña
                PreparedStatement pst = con.prepareStatement("INSERT INTO usuarios VALUES ('"+customer.getId()+"','"+customer.getName()+"');");
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

    public static Customer isInBase(Customer customerIN){
        CustomerControler customerControler=new CustomerControler();
        ArrayList<Customer> listaCust=new ArrayList<Customer>();
        //extraemos la lista de customers y vemos si el introducido figura en ella
        customerControler.getCustomer(listaCust);
        for (Customer customer : listaCust) {
            if (customerIN.equals(customer)) {
                return customer;
            }
        }
        return null;
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
            System.out.println("He leído el id: "+customer.getId()+" con nombre: "+customer.getName());
        }


    }

}