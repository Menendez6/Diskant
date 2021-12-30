package main.java.isw21.dao;
import main.java.isw21.descuentos.ChequeRegalo;
import main.java.isw21.descuentos.Oferta;
import main.java.isw21.descuentos.OfertaFactory;
import main.java.isw21.domain.Customer;
import main.java.isw21.excepciones.PorcentajeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase encargada de hacer las queries a la tabla de descuentos o usudescs
 * @version 0.3
 */
public class OfertaDAO {

    /**
     * Llama a la base de datos para añadir una oferta
     * @param customer el cliente a quien pertenece el descuento
     * @param oferta la oferta a añadir
     * @param tipo el tipo de oferta: 0 descuento, 1 porcentaje, 2 cheque.
     * @return
     */
    public static Oferta addDescuento(Customer customer, Oferta oferta, int tipo) {
        // iniciamos la conexion con la base de datos
        Connection con = ConnectionDAO.getInstance().getConnection();
        int gastado = 0;
        if (OfertaDAO.isInBase(oferta) == false) {
            try {
                // Añadimos el descuento a la tabla de descuentos con el formato previamente establecido.
                // El primer valor será el ID del dueño del descuento seguido por el descuento.
                PreparedStatement pst = con.prepareStatement("INSERT INTO descuentos VALUES ('" + oferta.getComercio() + "','" + oferta.getFechaIn() + "','"
                        + oferta.getFechaFin() + "','" + tipo + "','" + oferta.getValor() + "','" + oferta.getCodigo() +  "');");
                ResultSet rs = pst.executeQuery();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        try {
            // Añadimos el descuento a la tabla de descuentos con el formato previamente establecido.
            // El primer valor será el ID del dueño del descuento seguido por el descuento.
            PreparedStatement pst = con.prepareStatement("INSERT INTO usudescs VALUES ('" + customer.getUsuario() + "','" + oferta.getCodigo() + "','" + gastado + "');");
            ResultSet rs = pst.executeQuery();
            return oferta;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }


    /**
     * Llama a la base de datos para obtener los descuentos de un usuario
     * @param customer el cliente que solicita los descuentos
     * @param lista la lista con ofertas a la que se añaden los descuentos
     */
    public static ArrayList<Oferta> getDescuentos(ArrayList<Oferta> lista, Customer customer){
        Connection con = ConnectionDAO.getInstance().getConnection();
        if(lista == null || lista.size() == 0)
        {
            lista = new ArrayList<Oferta>();
        }
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usudescs WHERE usuario= '"+customer.getUsuario()+ "';");
             ResultSet rs = pst.executeQuery()) {
            OfertaFactory factoria = new OfertaFactory();
            while (rs.next()) {
                try (PreparedStatement pst1 = con.prepareStatement("SELECT * FROM descuentos WHERE codigo= '"+rs.getString(2)+ "';");
                     ResultSet rs1 = pst1.executeQuery()) {

                    while (rs1.next()) {

                        // Estos descuentos son añadidos a la lista que se ha pasado por parámetro.
                        Oferta oferta = factoria.getOferta(rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getInt(5),rs1.getString(6),rs1.getInt(4),rs.getDouble(3));
                        lista.add(oferta);
                    }
                } catch (PorcentajeException exc){
                    System.out.println(exc);
                }
                catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    /**
     * Método que devuelve todos los descuentos de la base de datos
     * @return todos los descuentos
     */
    private static ArrayList<Oferta> getDescuentosTotal(){
        //Como debemos extraer descuentos, necesitamos conexion a la base de datos, por lo que tenemos que gnerar una conexion
        Connection con = ConnectionDAO.getInstance().getConnection();
        //Si la lista que debemos actualizar no existe o si su tamaño es cero, la volvemos a crear

        ArrayList<Oferta> lista = new ArrayList<Oferta>();

        //Ejecutamos la query que obtiene los descuentos asociados a un usuario
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM descuentos;");
             ResultSet rs = pst.executeQuery()) {
            OfertaFactory factoria = new OfertaFactory();
            while (rs.next()) {
                // Estos descuentos son añadidos a la lista que se ha pasado por parámetro.
                Oferta oferta = factoria.getOferta(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getString(6),rs.getInt(4),0);
                lista.add(oferta);
            }
        } catch (PorcentajeException exc) {
            System.out.println(exc);
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    /**
     * Llama a la base de datos para eliminar una oferta
     * @param customer el cliente a quien pertenece el descuento
     * @param oferta la oferta a eliminar
     */
    public static void eliminarDescuento(Customer customer, Oferta oferta){
        Connection con = ConnectionDAO.getInstance().getConnection();
        try {
            // Añadimos el descuento a la tabla de descuentos con el formato previamente establecido.
            // El primer valor será el ID del dueño del descuento seguido por el descuento.
            PreparedStatement pst = con.prepareStatement("DELETE FROM usudescs WHERE descuento = '" + oferta.getCodigo() + "' AND usuario = '" + customer.getUsuario() + "';");
            ResultSet rs = pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Comprueba si un descuento ya está en la base de datos.
     * @param ofertaIN la oferta a comprobar
     * @return true si está y false si no
     */
    private static boolean isInBase(Oferta ofertaIN) {
        ArrayList<Oferta> listaDesc=new ArrayList<Oferta>();
        //extraemos la lista de customers y vemos si el introducido figura en ella
        listaDesc = OfertaDAO.getDescuentosTotal();
        for (Oferta oferta : listaDesc) {
            if (ofertaIN.equals(oferta)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Llama a la base de datos para actualizar lo que se ha gastado de una oferta
     * @param customer el cliente a quien pertenece el descuento
     * @param cheque el cheque que se ha utilizado
     */
    public static void updateGastado(Customer customer,ChequeRegalo cheque){
        Connection con = ConnectionDAO.getInstance().getConnection();
        try (PreparedStatement pst = con.prepareStatement("UPDATE usudescs SET gastado = " + cheque.getGastado() + " WHERE usuario = '" + customer.getUsuario()  + "' AND descuento = '" + cheque.getCodigo() + "';");
             ResultSet rs = pst.executeQuery()) {

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
