package main.java.isw21.dao;

import junit.framework.TestCase;
import main.java.isw21.descuentos.Oferta;
import main.java.isw21.descuentos.OfertaFactory;
import main.java.isw21.domain.Customer;
import main.java.isw21.excepciones.PorcentajeException;

import java.util.ArrayList;

/**
 * Clase para testear OfertaDAO
 * version 0.2
 */
public class OfertaDAOTest extends TestCase {
    private static OfertaDAO dDAO=null;
    private Customer customer= new Customer("test","test");
    private ArrayList<Oferta> lista= new ArrayList<Oferta>();

    public void setUp() throws Exception {

    }

    //probamos que el código detecta que hay duplicados en la tabla de descuentos
    public void testAddDescuento() {
        OfertaFactory factoria = new OfertaFactory();
        Oferta oferta = null;
        try {
            oferta = factoria.getOferta("ComercioTest","15/07/2010","15/08/2022",1,"test",1,0);
        } catch (PorcentajeException e) {
            e.printStackTrace();
        }
        assertEquals(null, OfertaDAO.addDescuento(customer,oferta,1));
        //new Descuento("ComercioTest","15/07/2010","15/08/2022",1,1,"test3")
    }

    public void testGetDescuentos() {
        // Comprobamos que la lista de los descuentos asociados al usuario test coincida con la esperada
        OfertaFactory factoria = new OfertaFactory();
        Oferta oferta = null;
        try {
            oferta = factoria.getOferta("ComercioTest","15/07/2010","15/08/2022",1,"test",1,0);
        } catch (PorcentajeException e) {
            e.printStackTrace();
        }
        lista.add(oferta);
        assertEquals(lista, OfertaDAO.getDescuentos(lista,customer));
    }
}