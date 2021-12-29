package main.java.isw21.controler;

import java.util.ArrayList;

import main.java.isw21.dao.CustomerDAO;
import main.java.isw21.domain.Customer;
//Controlador de customer, encargado de ejecutrar la lista de clientes que tiene la aplicacion
public class CustomerControler {

    public void getCustomer(ArrayList<Customer> lista) {
        CustomerDAO.getClientes(lista);
    }
}