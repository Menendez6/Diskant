package main.java.isw21.domain;

import java.io.Serializable;

public class Customer implements Serializable{
    /**
     *
     */
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