//Descripcion de la clase Descuento

package main.java.isw21.descuentos;

import main.java.isw21.excepciones.PorcentajeException;

import java.io.Serializable;

/**
 * Clase padre de todos los tipos de ofertas diferentes que hay
 */
public abstract class Oferta implements Serializable {

    // para definir un descuento, será necesario conocer diversos parametros:
    // Fecha de inicio, Fecha de fin, tipo --> porcentaje, descuento, dinero, cupon,... , valor --> valor total del descuento
    // Codigo unico del descuento para el comercio, y el comercio dueño del descuento
    public String fechaIn;
    public String fechaFin;
    //public int tipo;
    public int valor;
    public String codigo;
    public String comercio;

    public Oferta(String comercio, String fechaIn, String fechaFin,int valor, String codigo) {
        this.fechaIn=fechaIn;
        this.fechaFin=fechaFin;
        this.valor = valor;
        //this.tipo=tipo;
        this.comercio=comercio;
        this.codigo=codigo;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(String fechaIn) {
        this.fechaIn = fechaIn;
    }

    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    /*public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }*/

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) throws PorcentajeException {
        this.valor = valor;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    /**
     * Método para comparar dos ofertas, las consideramos igual si su código y su comercio es el mismo
     * @param o oferta a comparar
     * @return true si son iguales y false si no
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof Oferta){
            Oferta ofertaIN =(Oferta)o;
            if (ofertaIN.getCodigo().equals(this.codigo) && ofertaIN.getComercio().equals(this.comercio)){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }
}


