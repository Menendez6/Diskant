package main.java.isw21.io;


import main.java.isw21.descuentos.Descuento;
import main.java.isw21.descuentos.Oferta;

import java.io.*;
import java.util.ArrayList;

/**
 * Clase para trabajar con las apis. Patrón proxy.
 * Esta clase nos permite cargar las apis de diferentes entidades. Actualmente las tenemos en local, por eso usamos proxy.
 */
public class IODescuento {
    /**
     * Obtener las ofertas de una entidad
     * @param entidad
     * @return
     */
    public static ArrayList<Oferta> leerOfertas(String entidad){
        FileInputStream fis=null;
        ObjectInputStream ois= null;
        try{
            fis=new FileInputStream("src/main/java/isw21/apis/" +entidad+".ofrt");
            ois= new ObjectInputStream(fis);
            return (ArrayList<Oferta>) ois.readObject();
        }
        catch (EOFException eofe){
            try
            {
                ois.close();
                fis.close();
            }
            catch(IOException ioe)
            {
                System.out.println("No existen descuentos asociados a la entidad");
            }
        }
        catch(IOException ioe)
        {
            System.out.println("No existen descuentos asociados a la entidad");
        }
        catch(ClassNotFoundException cnfe)
        {
            System.out.println("No existen descuentos asociados a la entidad");
        }
        return new ArrayList<>();
    }

    /**
     * Añadir ofertas a una entidad. Este método es para un usuario administrador que quiera modificar los descuentos
     * de alguna entidad.
     * @param ofertas
     * @param entidad
     */
    public static void escribirOferta(ArrayList<Oferta> ofertas, String entidad){
        try{
            FileOutputStream fos = new FileOutputStream("src/main/java/isw21/apis/"+entidad+".ofrt"); //.ser
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ofertas);
            oos.close();
            fos.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
