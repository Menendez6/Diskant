package main.java.isw21.controler;

import main.java.isw21.dao.OfertaDAO;
import main.java.isw21.descuentos.ChequeRegalo;
import main.java.isw21.descuentos.Oferta;
import main.java.isw21.domain.Customer;

import java.util.ArrayList;

/**
 * Clase que actua como intermediario entre la el acceso a datos relacionados con ofertas y la fachada. Patrón MVC, controlador
 * @version 0.2
 * @see OfertaDAO
 */

public class OfertaControler {

    /**
     * Llama al DAO para añadir una oferta
     * @param customer el cliente a quien pertenece el descuento
     * @param oferta la oferta a añadir
     * @param tipo el tipo de oferta: 0 descuento, 1 porcentaje, 2 cheque.
     */
    public void addDescuento(Customer customer, Oferta oferta, int tipo) {
        OfertaDAO.addDescuento(customer,oferta,tipo);
    }
    /**
     * Llama al DAO para obtener los descuentos de un usuario
     * @param customer el cliente que solicita los descuentos
     * @param ofertas la lista con ofertas a la que se añaden los descuentos
     */
    public void getDescuentos(ArrayList<Oferta> ofertas, Customer customer) {
        OfertaDAO.getDescuentos(ofertas,customer);
    }

    /**
     * Llama al OfertaDAO para actualizar lo que se ha gastado de una oferta
     * @param customer el cliente a quien pertenece el descuento
     * @param cheque el cheque que se ha utilizado
     */
    public void updateGastado(Customer customer, ChequeRegalo cheque){
        OfertaDAO.updateGastado(customer,cheque);
    }

    /**
     * Llama al DAO para eliminar una oferta
     * @param customer el cliente a quien pertenece el descuento
     * @param oferta la oferta a eliminar
     */
    public void eliminarDescuento(Customer customer, Oferta oferta) {
        OfertaDAO.eliminarDescuento(customer, oferta);
    }
}
