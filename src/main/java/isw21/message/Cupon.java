
//Clase Cupon (pruebas)
package main.java.isw21.message;

public class Cupon {

    String marca;

    int dayIn;
    int monthIn;
    int yearIn;

    int expiration;



    public Cupon(String marca, int dayIn,int monthIn, int yearIn,int expiration){
        this.marca=marca;
        this.dayIn=dayIn;
        this.monthIn=monthIn;
        this.yearIn=yearIn;
        this.expiration=expiration;
    }

    public int getDayIn() {
        return dayIn;
    }

    public int getExpiration() {
        return expiration;
    }

    public int getMonthIn() {
        return monthIn;
    }

    public int getYearIn() {
        return yearIn;
    }

    public String getMarca() {
        return marca;
    }

    public void setDayIn(int dayIn) {
        this.dayIn = dayIn;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public void setMonthIn(int monthIn) {
        this.monthIn = monthIn;
    }

    public void setYearIn(int yearIn) {
        this.yearIn = yearIn;
    }
}
