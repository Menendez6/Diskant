package main.java.isw21.dao;

import junit.framework.TestCase;
import main.java.isw21.domain.Customer;

import java.util.ArrayList;

/**
 * Clase para testear el customerDAO utilizando JUnit
 * @version 0.2
 */
public class CustomerDAOTest extends TestCase {
    public ArrayList<Customer> clientes = new ArrayList<>();
    public ArrayList<Customer> clientesVac = new ArrayList<>();

    public Customer primerUsr= new Customer("Pablo","201902237","201902237@alu.comillas.edu",0,0,0,0);
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testGetClientes() {
        clientes.add(primerUsr);
        assertEquals(clientes.get(0),CustomerDAO.getClientes(clientesVac).get(0));
    }

    public void testAddCliente() {
        //Prueba erronea para ver comportamiento correcto
        // Introducimos un usuario ya existente en la base de datos, por lo que no nos deberia dejar y su respuesta debe ser null
        assertEquals(null,CustomerDAO.addCliente(new Customer("test","test")));
    }

    public void testIsInBase() {
        assertEquals(new Customer("Pablo", "201902237"),CustomerDAO.isInBase(primerUsr));
    }
}