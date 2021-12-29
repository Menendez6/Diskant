// Descripcion de la calse sutomer
package main.java.isw21.domain;

import java.io.Serializable;

public class Customer implements Serializable{
    /**
     *
     */
    // Por ahora solo se almacenará la contrseña y el nombre de usuario. En el rpoximo Sprint se añadiran más
    // como por ejemplo mail, numero de telefono y grupos a los que pertenece.

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;

    public Customer() {
        this.setId(new String());
        this.setName(new String());
    }

    public Customer(String id, String name) {
        this.setName(name);
        this.setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Metodo equals --> Establecemos que para que un usuario sea igual a otro debe tener el nombre y la contraseña igual
    //En un futuro cambiará y se podrá acceder tambien por mail y por correo electrónico.
    // El método es util  en las funciones de añadir usuario y login.
    @Override
    public boolean equals(Object o){
        if (o instanceof Customer){
            Customer customerIN=(Customer)o;
            if (customerIN.getId().equals(this.id) && customerIN.getName().equals(this.name)){
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