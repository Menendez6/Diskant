
package main.java.isw21.client;

//Importamos todas la clases neceaarios para el correcto funcionamiento de la conexión
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.isw21.descuentos.ChequeRegalo;
import main.java.isw21.descuentos.Oferta;
import org.apache.log4j.Logger;

import main.java.isw21.domain.Customer;
import main.java.isw21.message.Message;

/**
 * Clase encargada del dialogo entre servidor y cliente
 * @version 0.1
 */

public class Client {


    //Establecemos todos los campos que necesarios para dicha conexión: host, port, nombre del cliente, contexto, id, identifiación.logger, descuentos asociados a la cuenta y la session (elemento que viaja entre el servidor
    // el cliente y continene toda la informacion para el correcto funcionamiento del servidor)
    private final String host;
    private final int port;
    static String nombre;
    static String context;
    static String email;
    static String id;
    static Customer identification;
    final static Logger logger = Logger.getLogger(Client.class);

    /**
     * Hashmap que se utiliza para pasar información entre cliente y servidor
     */
    public HashMap<String,Object> session=new HashMap<String, Object>();
    public ArrayList<Oferta> ofertas;


    /**
     * El metodo run nos servirá para poder realizar la conexion entre cliente y servidor.
     * Este método utilizará la informacion aosciada previamente al cliente como el contexto en el que se ejecuta o los campos necesarios para su desarrollo.
     * @param cliente cliente con el que se asocia
     */

    public void run(Client cliente) {


        System.out.println(context);

        Message mensajeEnvio=new Message();
        Message mensajeVuelta=new Message();

        mensajeEnvio.setContext(context);
        //Creamos los mensajes de salida y de entrada del cliente.
        //Prueba para id de customer
        Customer customer2= new Customer(id, nombre);
        //System.out.println("El email es:"+email);
        customer2.setEmail(email);
        mensajeEnvio.setCustomer(customer2);
        mensajeEnvio.setSession(session);

        //
        /*Customer cust = (Customer) session.get("Customer");
        System.out.println("Se esta iniciando "+cust.getUsuario());
        System.out.println("Contraseña: "+cust.getContraseña());*/

        //

        cliente.sent(mensajeEnvio,mensajeVuelta);

        //En el switch case establecemos qué se debe realizar con el mensaje devuelto por el servidor.

        switch (mensajeVuelta.getContext()) {
            //La respuesta al añadido de un descuento será el descuento añadido. Si este ya figura en la base de datos este será nulo.

            case "/addDescuentoResponse":
                Oferta oferta =(Oferta) (mensajeVuelta.getSession().get("Descuento"));
                break;
            // Obtencion de los decuentos: estableceremos los descuentos del cliente como la respuesta del servidor. Asi ya tendremos el arrayList de todos los descuentos asociados

            case "/getDescuentosResponse":
                ofertas = (ArrayList<Oferta>) (mensajeVuelta.getSession().get("Descuentos"));
                break;
            // En caso de la devolucion del mensaje de añadio de un usuario, estableceremos como correcta (true) la identificacion del customer en el cliente. Si este no se ha identificado correctamente, no podrá acceder a los descuentos

            case "/addNewUserResponse":
                // Iniciamos siempre partiendo de que la identificacion ha sido erronea
                identification=null;
                // Con el mensaje de vuelta vemos si el insertado del nuevo usuario se ha podido realizar
                Customer temp=(Customer)(mensajeVuelta.getSession().get("Customer"));
                if (temp!=null){
                    System.out.println("El usuario se ha añadido a la base");
                    identification=temp;
                }
                else{
                    System.out.println("El usuario a añadir ya se encuentra en la base");
                }
                break;
            //Caso de ejemplo
            case "/getCustomerResponse":
                ArrayList<Customer> customerList=(ArrayList<Customer>)(mensajeVuelta.getSession().get("Customer"));
                for (Customer customer : customerList) {
                    System.out.println("He leído el usuario: "+customer.getUsuario()+" con contraseña: "+customer.getContraseña());
                }
                break;

            // Al igual que en el caso del new user, solo se podrá acceder a los decuentos si se acaba de registar o el login ha sido satisfactorio.
            case "/getAccessResponse":
                //Extraemos el valor asociado a Customer en la sesion del mensaje de vuelta
                // En el mensaje de vuelta, el servidor habrá puesto como null o como un customer este campo
                // dependiendo de si la identificación ha sido correcta o no.
                // Si la respuesta del servidor es un customer, la identificacion ha sido correcta y desde ese momento
                // El usuario podrá acceder a todo su contenido
                identification = (Customer)(mensajeVuelta.getSession().get("Customer"));
                break;
            case "/updateChequeResponse":
                ChequeRegalo cheque = (ChequeRegalo) mensajeVuelta.getSession().get("Cheque");
                break;
            case "/updatePorcentajeResponse":
                double ahorrado = (Double) mensajeVuelta.getSession().get("Ahorrado");
                break;
            case "/updateDescuentoResponse":
                ahorrado = (Double) mensajeVuelta.getSession().get("Ahorrado");
                break;

            default:
                Logger.getRootLogger().info("Option not found");
                System.out.println("\nError a la vuelta");
                break;

        }
        //System.out.println("3.- En Main.- El valor devuelto es: "+((String)mensajeVuelta.getSession().get("Nombre")));
    }

    /**
     * Para iniciar el cliete, primero debemos haberlo creado, para ello debemos especificar en que puerto y se aloja
     * y su host.
     * @param host
     * @param port
     */
    public Client(String host, int port) {
        this.host=host;
        this.port=port;
    }


    /**
     * Método de envio y recibo de mensajes entre servidor y cliente
     * @param messageOut mensaje que envía al servidor
     * @param messageIn mensaje que recibe del servidor
     */
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
                System.out.println(messageOut);

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

    //Metodos setteers y getters necesarios para el correcto funcionamiento de la clase
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

    public static Customer getIdentification() {
        return identification;
    }

    public static void setIdentification(Customer identification) {
        Client.identification = identification;
    }

    public static void setContext(String context) {
        Client.context = context;
    }

    public static String getContext() {
        return context;
    }

    public void setSession(HashMap<String, Object> session) {
        this.session = session;
    }

    public HashMap<String, Object> getSession() {
        return session;
    }

    public void setDescuentos(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public ArrayList<Oferta> getDescuentos() {
        return ofertas;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Client.email = email;
    }
}