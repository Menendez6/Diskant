// Descripcion de la clase comercio
package main.java.isw21.comercio;

import java.io.Serializable;
public class Comercio implements Serializable {
    String direccion;
    String nombre;
    String horario;

    // Para poder crear un comercio primero deberemos especificar su nombre y direccion
    // Como proximos features se introduciran la opcion de pertencer a cadenas.

    // Ademas se asociará su logo al comercio para poder ser mas visual. Tambien se añadirá su horario para poder obtener mas informacion
    public Comercio(String nombre, String direccion){
        this.direccion=direccion;
        this.nombre=nombre;
    }
}
