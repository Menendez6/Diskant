package main.java.isw21.descuentos;

import main.java.isw21.excepciones.PorcentajeException;

public class Porcentaje extends Oferta {
    public Porcentaje(String comercio, String fechaIn, String fechaFin, int valor, String codigo) throws PorcentajeException {
        super(comercio, fechaIn, fechaFin,valor, codigo);
        this.setValor(valor);
    }

    @Override
    public void setValor(int valor) throws PorcentajeException {
        if (valor > 0 && valor <= 100){
            this.valor = valor;
        }else{
            throw new PorcentajeException();
        }
    }

    //Añadir excepcion de no mayor que 100


}
