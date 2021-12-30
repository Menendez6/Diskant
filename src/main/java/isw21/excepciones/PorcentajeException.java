package main.java.isw21.excepciones;

import main.java.isw21.descuentos.Porcentaje;

/**
 * Excepción para que el valor del porcentaje esté entre 0 y 100
 */
public class PorcentajeException extends Exception{

    @Override
    public String toString(){
        return "El valor del porcentaje debe estar entre 0 y 100";
    }
}
