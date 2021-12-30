// Descripcion de la calse sutomer
package main.java.isw21.domain;

import main.java.isw21.controler.CustomerControler;

import java.io.Serializable;

/**
 * Clase para los clientes
 * @version 0.3
 */
public class Customer implements Serializable{

    // Por ahora solo se almacenará la contrseña y el nombre de usuario. En el rpoximo Sprint se añadiran más
    // como por ejemplo mail, numero de telefono y grupos a los que pertenece.

    private static final long serialVersionUID = 1L;
    private String usuario;
    private String contraseña;
    private String email;
    private double ahorrado[] = new double[3];
    private int numPorcentajes;

    public Customer() {
        this.setUsuario(new String());
        this.setContraseña(new String());
    }

    public Customer(String user, String pass) {
        this.setUsuario(user);
        this.setContraseña(pass);
        this.email = "fake@fake.com";
        this.ahorrado[0] = 0;
        this.ahorrado[1] = 0;
        this.ahorrado[2] = 0;
        this.numPorcentajes = 0;
    }
    public Customer(String user, String pass, String email, double descuento, double porcentaje, double regalo, int numPorcentajes) {
        this.setUsuario(user);
        this.setContraseña(pass);
        this.email = email;
        this.ahorrado[0] = descuento;
        this.ahorrado[1] = porcentaje;
        this.ahorrado[2] = regalo;
        this.numPorcentajes = numPorcentajes;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String id) {
        this.usuario = id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String name) {
        this.contraseña = name;
    }

    //Metodo equals --> Establecemos que para que un usuario sea igual a otro debe tener el nombre y la contraseña igual
    //En un futuro cambiará y se podrá acceder tambien por mail y por correo electrónico.
    // El método es util  en las funciones de añadir usuario y login.

    /**
     * Establecemos que para que un usuario sea igual a otro debe tener el nombre y la contraseña igual.
     * El método es util  en las funciones de añadir usuario y login.
     * @param o el customer a comparar
     * @return true si es el mismo y false si no.
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof Customer){
            Customer customerIN=(Customer)o;
            if (customerIN.getUsuario().equals(this.usuario) && customerIN.getContraseña().equals(this.contraseña)){
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public double[] getAhorrado() {
        return ahorrado;
    }

    /**
     * Esta función nos permite modificar los valores de ahorro del usuario
     * @param tipo el tipo de descuento a modificar
     * @param valor el valor añadido que se ha ahorrado al utilizar una oferta
     */
    public void setAhorrado(String tipo, double valor){
        CustomerControler controler = new CustomerControler();
        if (tipo == "Descuento") {
            double valorDescuento = this.ahorrado[0] + valor;
            this.ahorrado[0] = valorDescuento;
            //controler.updateDescuento(this, valorDescuento);//Lo actualizamos también en la base de datos
        }
        if (tipo == "Porcentaje"){
            double valorPorcentaje;
            numPorcentajes ++;
            valorPorcentaje = ((this.ahorrado[1]*(numPorcentajes-1)) + valor)/numPorcentajes;
            this.ahorrado[1] = valorPorcentaje;
            //controler.updatePorcentaje(this, valorPorcentaje, numPorcentajes);//Lo actualizamos también en la base de datos
        }
        if (tipo == "Cheque"){
            double valorCheque = this.ahorrado[2] + valor;
            this.ahorrado[2] = valorCheque;
            //controler.updateCheque(this,valorCheque);
        }
    }

    public int getNumPorcentajes(){
        return this.numPorcentajes;
    }

}