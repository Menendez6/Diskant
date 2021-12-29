package main.java.isw21.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import main.java.isw21.configuration.PropertiesISW;
import main.java.isw21.domain.Customer;
import main.java.isw21.message.Message;

public class Client {
    private final String host;
    private final int port;
    static String nombre;
    static String context;
    static String id;
    static Boolean identification=false;
    final static Logger logger = Logger.getLogger(Client.class);

    public static void run(Client cliente) {
        //Configure connections
        /*String host = PropertiesISW.getInstance().getProperty("host");
        int port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
        Logger.getRootLogger().info("Host: "+host+" port"+port);
        //Create a cliente class
        Client cliente=new Client(host, port);*/

        HashMap<String,Object> session=new HashMap<String, Object>();
        //session.put("/getCustomer","");

        Message mensajeEnvio=new Message();
        Message mensajeVuelta=new Message();

        mensajeEnvio.setContext(context);

        //Prueba para id de customer
        Customer prueba= new Customer(id, nombre);
        mensajeEnvio.setCustomer(prueba);
        mensajeEnvio.setSession(session);
        cliente.sent(mensajeEnvio,mensajeVuelta);


        switch (mensajeVuelta.getContext()) {
            case "/addNewUserResponse":
                identification=false;
                Boolean temp=(Boolean)(mensajeVuelta.getSession().get("Customer"));
                if (temp){
                    System.out.println("El usuario se ha añadido a la base");
                    identification=true;
                }
                else{
                    System.out.println("El usuario a añadir ya se encuentra en la base");
                }
                break;
            case "/getCustomerResponse":
                ArrayList<Customer> customerList=(ArrayList<Customer>)(mensajeVuelta.getSession().get("Customer"));
                for (Customer customer : customerList) {
                    System.out.println("He leído el id: "+customer.getId()+" con nombre: "+customer.getName());
                }
                break;

            case "/getAccessResponse":
                identification = (Boolean)(mensajeVuelta.getSession().get("Customer"));
                break;





            default:
                Logger.getRootLogger().info("Option not found");
                System.out.println("\nError a la vuelta");
                break;

        }
        //System.out.println("3.- En Main.- El valor devuelto es: "+((String)mensajeVuelta.getSession().get("Nombre")));
    }

    public Client(String host, int port) {
        this.host=host;
        this.port=port;
    }


    public void sent(Message messageOut, Message messageIn) {
        try {

            System.out.println("Connecting to host " + host + " on port " + port + ".");

            Socket echoSocket = null;
            OutputStream out = null;
            InputStream in = null;

            try {
                echoSocket = new Socket(host, port);
                in = echoSocket.getInputStream();
                out = echoSocket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);

                //Create the objetct to send
                objectOutputStream.writeObject(messageOut);

                // create a DataInputStream so we can read data from it.
                ObjectInputStream objectInputStream = new ObjectInputStream(in);
                Message msg=(Message)objectInputStream.readObject();
                messageIn.setContext(msg.getContext());
                messageIn.setSession(msg.getSession());
		        /*System.out.println("\n1.- El valor devuelto es: "+messageIn.getContext());
		        String cadena=(String) messageIn.getSession().get("Nombre");
		        System.out.println("\n2.- La cadena devuelta es: "+cadena);*/

            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + host);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }

            /** Closing all the resources */
            out.close();
            in.close();
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static Boolean getIdentification() {
        return identification;
    }

    public static void setIdentification(Boolean identification) {
        Client.identification = identification;
    }

    public static void setContext(String context) {
        Client.context = context;
    }

    public static String getContext() {
        return context;
    }
}