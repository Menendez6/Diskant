package main.java.isw21.message;

import main.java.isw21.domain.Customer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
    /**
     *
     */
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