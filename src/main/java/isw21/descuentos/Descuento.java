package main.java.isw21.descuentos;

import main.java.isw21.excepciones.PorcentajeException;

/**
 * Clase de descuento, hereda de oferta
 * @see Oferta
 * @version 0.3
 */
public class Descuento extends Oferta{

    public Descuento(String comercio, String fechaIn, String fechaFin, int valor, String codigo) {
        super(comercio, fechaIn, fechaFin,valor, codigo);
    }

}
