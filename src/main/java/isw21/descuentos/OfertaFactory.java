package main.java.isw21.descuentos;

import main.java.isw21.excepciones.PorcentajeException;

/**
 * Clase que se encarga de crear ofertas distintos tipos de ofertas.
 * @version 0.3
 */
public class OfertaFactory extends AbstractFactory{
    @Override
    public Oferta getOferta(String txtEntidadText, String fechaIni, String fechaFin, int i, String text, int p, double gastado) throws PorcentajeException {
        if (p == 0){
            return new Descuento(txtEntidadText,fechaIni,fechaFin,i,text);
        }else if(p ==1){
            return new Porcentaje(txtEntidadText,fechaIni,fechaFin,i,text);
        }else if(p == 2){
            return new ChequeRegalo(txtEntidadText,fechaIni,fechaFin,i,text,gastado);
        }
        return null;
    }
}
