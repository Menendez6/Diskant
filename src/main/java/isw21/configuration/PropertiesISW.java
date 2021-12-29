package main.java.isw21.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
//Codigo de conexion a la base de datos utilizado por el servidor
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

    public static PropertiesISW getInstance() {
        if (prop==null) {
            prop=new PropertiesISW();
        }
        return prop;

    }

}