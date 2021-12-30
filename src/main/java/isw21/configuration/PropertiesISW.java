package main.java.isw21.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * Codigo para acceder a la configuración properties.xml, que contiene la información del servidor. Singleton.
 * @version 0.1
 */
public class PropertiesISW extends Properties{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static PropertiesISW prop;
    private static final String path="properties/properties.xml";


    private PropertiesISW() {
        try {
            this.loadFromXML(new FileInputStream(path));
            //this.loadFromXML(getClass().getClassLoader().getResourceAsStream("properties.xml"));

        } catch (InvalidPropertiesFormatException e) {
            // TOD O Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TOD O Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TO DO Auto-generated catch block
            e.printStackTrace();
        };
    }

    /**
     * Método utilizado para aplicar el patrón Singleton, es decir que tengamos una sola instancia.
     * @return un properties nuevo si es la primera vez que se crea en el código. Si ya está creado previamente, se devuelve el objeto Properties ya creado.
     * De esta forma consigues que solo haya una instancia.
     */
    public static PropertiesISW getInstance() {
        if (prop==null) {
            prop=new PropertiesISW();
        }
        return prop;

    }

}