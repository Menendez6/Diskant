package main.java.isw21.dao;

import junit.framework.TestCase;
import main.java.isw21.descuentos.Descuento;
import main.java.isw21.domain.Customer;

import java.util.ArrayList;

public class DescuentoDAOTest extends TestCase {
    private static DescuentoDAO dDAO=null;
    private Customer customer= new Customer("test","test");
    private ArrayList<Descuento> lista= new ArrayList<Descuento>();

    public void setUp() throws Exception {

    }

    //probamos que el código detecta que hay duplicados en la tabla de descuentos
    public void testAddDescuento() {
        assertEquals(null,DescuentoDAO.addDescuento(customer,new Descuento("ComercioTest","15/07/2010","15/08/2022",1,1,"test3")));
        //new Descuento("ComercioTest","15/07/2010","15/08/2022",1,1,"test3")
    }

    public void testGetDescuentos() {
        // Comprobamos que la lista de los descuentos asociados al usuario test coincida con la esperada
        lista.add(new Descuento("ComercioTest","15/07/2010","15/08/2022",1,1,"test"));
        assertEquals(lista,DescuentoDAO.getDescuentos(lista,customer));
    }
}