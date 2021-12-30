package main.java.isw21.io;

import junit.framework.TestCase;

/**
 * Clase para probar que la importación de descuentos funciona correctamente
 * @version 0.3
 */

public class IODescuentoTest extends TestCase {
    public void testOfertas(){
        assertEquals(IODescuento.leerOfertas("comillas").toArray().length, 3);
    }
    public void testOfertas1(){
        assertEquals(IODescuento.leerOfertas("carrefour").toArray().length, 0);
    }
}
