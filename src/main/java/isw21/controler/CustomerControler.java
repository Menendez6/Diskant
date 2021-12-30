package main.java.isw21.controler;

import java.util.ArrayList;

import main.java.isw21.dao.CustomerDAO;
import main.java.isw21.domain.Customer;


/**
 * Controlador de customer, encargado de ejecutrar la lista de clientes que tiene la aplicacion. Patrón MVC, controlador
 * @version 0.1
 * @see CustomerDAO
 */
public class CustomerControler {
    /**
     * Llama al DAO para obtener un cliente
     * @param lista la lista de clientes que devuelve
     */
    public void getCustomer(ArrayList<Customer> lista) {
        CustomerDAO.getClientes(lista);
    }

    /**
     * Llama al DAO para comprobar si el usuario está en la base de datos
     * @param customerIn el customer que queremos ver si está en la base de datos
     * @return El customer si está en la base y null si no está
     */
    public Customer isInBase(Customer customerIn){return CustomerDAO.isInBase(customerIn);}

    /**
     * Llama al DAO para actualizar la cantidad ahorrada en descuentos del usuario
     * @param customer el cliente a quien pertence el descuento gastado
     * @param valor el valor del descuento
     */
    public void updateDescuento(Customer customer,double valor){CustomerDAO.updateDescuento(customer,valor);}

    /**
     * Llama al DAO para actualizar la cantidad ahorrada en cheques del usuario
     * @param customer el cliente a quien pertence el cheque gastado
     * @param valorCheque el valor de la cantidad gastada del cheque
     */
    public void updateCheque(Customer customer, double valorCheque) {CustomerDAO.updateCheque(customer,valorCheque);
    }

    /**
     * Llama al DAO para actualizar la cantidad ahorrada en porcentajes del usuario
     * @param customer el cliente a quien pertence el porcentaje gastado
     * @param valorPorcentaje el valor del porcentaje
     * @param numPorcentajes el número de porcentajes usados (para hacer la media)
     */
    public void updatePorcentaje(Customer customer, double valorPorcentaje, int numPorcentajes) {CustomerDAO.updatePorcentaje(customer,valorPorcentaje,numPorcentajes);
    }

    /**
     * LLama al DAO para añadir un cliente a la base de datos
     * @param customerIN el cliente a añadir
     */
    public void addCliente(Customer customerIN) { CustomerDAO.addCliente(customerIN);
    }
}