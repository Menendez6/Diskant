// Descripción de la clase Message. Es la encargada del transporte de la información entre servidor y cliente
package main.java.isw21.message;

import main.java.isw21.domain.Customer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase para crear mensajes que se transmiten entre cliente y servidor
 * @version 0.1
 */
public class Message implements Serializable {
    /**
     *
     */
    // Establecemos como parámetros el contexto del mensaje y un hashmap en el que poder intercambiar todos los datos
    // necesarios para el correcto funcionamiento de los métodos en cliente (client) y servidor (SocketServer)
    private static final long serialVersionUID = 1L;
    private String context;
    private Map<String, Object> session;
    private Boolean correct;
    private Customer customer;


    public Message () {
        context=new String();
        session=new HashMap<String, Object>();
        correct= false;

    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public void setCustomer(Customer customer){
        this.customer=customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}