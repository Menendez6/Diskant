// descripcion clase SocketServer: Servidor y conexion a base de datos.

package main.java.isw21.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.isw21.controler.CustomerControler;
import main.java.isw21.controler.OfertaControler;
import main.java.isw21.descuentos.ChequeRegalo;
import main.java.isw21.descuentos.Oferta;
import main.java.isw21.domain.Customer;
import main.java.isw21.message.Message;

/**
 * Clase servidor que dialoga con el cliente para manipular la información necesaria para el funcionamiento de la aplicación a través de los DAOs.
 * @version 0.3
 */
public class SocketServer extends Thread {
    public static final int PORT_NUMBER = 8081;

    protected Socket socket;

        // Entrada de cliente en el servidor
    public SocketServer(Socket socket) {
        this.socket = socket;
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }

    /**
     * Código de ejecución continua en el servidor
     */
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        Customer customer;
        double ahorrado;
        try {

            in = socket.getInputStream();
            out = socket.getOutputStream();
            //Obtenemos los mensajes de entrada y de salida provistos por el cliente
            //first read the object that has been sent
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            Message mensajeIn= (Message)objectInputStream.readObject();
            //Object to return informations
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            Message mensajeOut=new Message();
            System.out.println("Server working");
            CustomerControler controler=new CustomerControler();
            OfertaControler ofertaControler = new OfertaControler();
            // Dividimos todos los casos de entrada de mensaje:
            switch (mensajeIn.getContext()) {
                //Por defecto, pondremos todas las evoluciones al cliente con el mismo contexto de entrada seguido de Response

                //Caso de obtencion de los usuarios alojados en la base de datos
                case "/getCustomer":
                    //Instanciamos el objeto que controla las listas de usuarios y la lista donde queremos alojarlo

                    ArrayList<Customer> lista=new ArrayList<>();
                    controler.getCustomer(lista);
                    //Iniciamos la construccion del mensaje de vuelta, contexto y la lista rellena de los usuarios
                    mensajeOut.setContext("/getCustomerResponse");
                    HashMap<String,Object> session=new HashMap<>();
                    session.put("Customer",lista);
                    mensajeOut.setSession(session);
                    //Mandamos el mensaje de respuesta
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                // Caso del logIn
                case "/getAccess":
                    //Cuando el usuario quiera identificarse, el servidor recogerá el customer introducido por el usuario
                    // Y el servidor confirmará que el customer introducido es el mismo que el que figura en la base de datos
                    //Customer customerIN= mensajeIn.getCustomer();
                    //CustomerControler controler=new CustomerControler();
                    mensajeOut.setContext("/getAccessResponse");

                    session=new HashMap<>();
                    Customer customerIN =(Customer) mensajeIn.getSession().get("Customer");

                    //session.put("Customer",null);
                    if (controler.isInBase(customerIN)!=null){
                        // Si figura en la base, se modificará la salida y se pondrá a true. Cabe destacar que esta salida será la que determine si la
                        // autentificacion ha sido correcta
                        session.put("Customer",controler.isInBase(customerIN));
                        System.out.println("Se ha autenticado el usuario: "+customerIN.getUsuario());
                    }
                    else{
                        // En caso contrario será false
                        session.put("Customer",null);
                        System.out.println("Se ha introducido mal la contraseña para el ID"+customerIN.getUsuario());
                    }
                    mensajeOut.setSession(session);
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                //Caso de añadir usuario
                case "/addNewUser":
                    // Cogemos el usuairo del mensaje de entrada y preparamos el mensaje de salida
                    customerIN = mensajeIn.getCustomer();
                    mensajeOut.setContext("/addNewUserResponse");
                    session=new HashMap<>();
                    //Marcamos como false el mensaje de vuelta
                    session.put("Customer",null);
                    if (controler.isInBase(customerIN)!=null){
                        // Si el customer ya se encuntra en la base de datos, no modifcamos el valor
                        session.put("Customer",null);
                    }
                    else{
                        System.out.println("El email del servidor a guardar es:"+customerIN.getEmail());
                        //En caso contrario, ejecutamos el método de añadido del usuario
                        controler.addCliente(customerIN);
                        //Tras haber ejecutado el añadido, comprobamos si realmente se ha añadido, en ese caso
                        // se modifica a true en el mensaje de vuelta.
                        if (controler.isInBase(customerIN)!=null){
                            session.put("Customer",controler.isInBase(customerIN));
                        }
                    }
                    mensajeOut.setSession(session);
                    // Mandamos el mensaje al cliente de vuelta
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/updateDescuento":
                    mensajeOut.setContext("/updateDescuentoResponse");
                    customer =(Customer) mensajeIn.getSession().get("Customer");
                    ahorrado= (Double) mensajeIn.getSession().get("Ahorrado");
                    //int numperc = (Integer) mensajeIn.getSession().get("Numero");
                    controler.updateDescuento(customer,ahorrado);
                    session=new HashMap<String, Object>();
                    //Mandaremos como salida el descuento añadido
                    session.put("Ahorrado", ahorrado);
                    mensajeOut.setSession(session);
                    //Lo mandamos de vuelta
                    objectOutputStream.writeObject(mensajeOut);
                    System.out.println("Se ha actualizado la cantidad ahorrada en descuento");
                    //Se debe inlcuir codigo para evitar introucir descuentos repetidos
                    break;
                case "/updatePorcentaje":
                    mensajeOut.setContext("/updatePorcentajeResponse");
                    customer =(Customer) mensajeIn.getSession().get("Customer");
                    ahorrado= (Double) mensajeIn.getSession().get("Ahorrado");
                    int numperc = (Integer) mensajeIn.getSession().get("Numero");
                    controler.updatePorcentaje(customer,ahorrado,numperc);
                    session=new HashMap<String, Object>();
                    //Mandaremos como salida el descuento añadido
                    session.put("Ahorrado", ahorrado);
                    mensajeOut.setSession(session);
                    //Lo mandamos de vuelta
                    objectOutputStream.writeObject(mensajeOut);
                    System.out.println("Se ha actualizado la cantidad ahorrada en porcentajes");
                    //Se debe inlcuir codigo para evitar introucir descuentos repetidos
                    break;
                case "/updateCheque":
                    mensajeOut.setContext("/updateChequeResponse");
                    customer =(Customer) mensajeIn.getSession().get("Customer");
                    ChequeRegalo cheque = (ChequeRegalo) mensajeIn.getSession().get("Cheque");
                    ahorrado = (Double) mensajeIn.getSession().get("Ahorrado");
                    controler.updateCheque(customer,ahorrado);
                    ofertaControler.updateGastado(customer,cheque);
                    session=new HashMap<String, Object>();
                    //Mandaremos como salida el descuento añadido
                    session.put("Cheque", cheque);
                    mensajeOut.setSession(session);
                    //Lo mandamos de vuelta
                    objectOutputStream.writeObject(mensajeOut);
                    System.out.println("Se ha actualizado la cantidad ahorrada en cheques");
                    //Se debe inlcuir codigo para evitar introucir descuentos repetidos
                    break;
                // Caso de añadir descuento
                case "/addDescuento":
                    //Iniciamos el mensaje de vuelta
                    mensajeOut.setContext("/addDescuentoResponse");
                    // Extraemos los valores necesarios para el añadido de descuentos: el dueño (custgomer) y el descuento a añadir en la base de datos
                    customer =(Customer) mensajeIn.getSession().get("Customer");
                    Oferta oferta = (Oferta) mensajeIn.getSession().get("Descuento");
                    int tipo = (Integer) mensajeIn.getSession().get("Tipo");
                    //Ejectuamos la funcion de añadir descuento a la base de datos
                    ofertaControler.addDescuento(customer, oferta, tipo);
                    //Finalizamos el mensaje de salida y lo mandamos
                    session=new HashMap<String, Object>();
                    //Mandaremos como salida el descuento añadido
                    session.put("Descuento", oferta);
                    mensajeOut.setSession(session);
                    //Lo mandamos de vuelta
                    objectOutputStream.writeObject(mensajeOut);
                    System.out.println("Se ha añadido el descuento");
                    //Se debe inlcuir codigo para evitar introucir descuentos repetidos
                    break;

                // Metodo de extraccion de descuentos de la base de datos
                case "/getDescuentos":
                    mensajeOut.setContext("/getDescuentosResponse");
                    //Extraemos del mensaje entrante, proveniente del cliente, el usuario del que queremos obtener los descuentos
                    customer =(Customer) mensajeIn.getSession().get("Customer");
                    //Extraemos tambien los descuentos
                    ArrayList<Oferta> ofertas = (ArrayList<Oferta>) mensajeIn.getSession().get("Descuentos");
                    //Llamamos al metodo getDescuentos, el cual actualiza la lista de los descuentos del customer pasado como parámetro
                    ofertaControler.getDescuentos(ofertas,customer);
                    //Construimos la respuesta
                    session=new HashMap<String, Object>();
                    session.put("Descuentos", ofertas);
                    mensajeOut.setSession(session);
                    //Una vez actualizada la lista y construida la respuesta, enviamos el mensaje de vuelta
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/eliminarOferta":
                    mensajeOut.setContext("/addDescuentoResponse");
                    customer =(Customer) mensajeIn.getSession().get("Customer");
                    oferta = (Oferta) mensajeIn.getSession().get("Oferta");
                    ofertaControler.eliminarDescuento(customer,oferta);
                    session=new HashMap<String, Object>();
                    session.put("Descuento", oferta);
                    mensajeOut.setSession(session);
                    //Una vez actualizada la lista y construida la respuesta, enviamos el mensaje de vuelta
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                default:
                    System.out.println("\nParámetro no encontrado");
                    break;

            }

            //Lógica del controlador
            //System.out.println("\n1.- He leído: "+mensaje.getContext());
            //System.out.println("\n2.- He leído: "+(String)mensaje.getSession().get("Nombre"));



            //Prueba para esperar
		    /*try {
		    	System.out.println("Me duermo");
				Thread.sleep(15000);
				System.out.println("Me levanto");
			} catch (InterruptedException e) {
				// TO DO Auto-generated catch block
				e.printStackTrace();
			}*/
            // create an object output stream from the output stream so we can send an object through it
			/*ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);

			//Create the object to send
			String cadena=((String)mensaje.getSession().get("Nombre"));
			cadena+=" añado información";
			mensaje.getSession().put("Nombre", cadena);
			//System.out.println("\n3.- He leído: "+(String)mensaje.getSession().get("Nombre"));
			objectOutputStream.writeObject(mensaje);*
			*/

        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
        } catch (ClassNotFoundException e) {
            // TO DO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    //Metodo el cual sirve para saber si algún customer ya esta en la base de datos, si es asi, lo devuelve si no, devuelve null
    /*public Customer isInBase(Customer customerIN){
        CustomerControler customerControler=new CustomerControler();
        ArrayList<Customer> listaCust=new ArrayList<Customer>();
        //extraemos la lista de customers y vemos si el introducido figura en ella
        customerControler.getCustomer(listaCust);
        for (Customer customer : listaCust) {
            if (customerIN.equals(customer)) {
                return customer;
            }
        }
        return null;
    }*/

    public static void main(String[] args) {
        System.out.println("SocketServer Example");
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */
                new SocketServer(server.accept());
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
            ex.printStackTrace();
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    //Metodo de añadir clientes
    /*public Customer addCliente(Customer customer){
        //Deberemos comprobar que el cliente a añadir no se encuentra en la base de datos
        if (this.isInBase(customer)==null){
            //Sabiendo que no figura en la base, ejecutamos la QUERY necesaria para añadir el usuario a la tabla de usuarios
            Connection con= ConnectionDAO.getInstance().getConnection();
            try{
                //La query introducira el nuevo usuario y su contraseña
                PreparedStatement pst = con.prepareStatement("INSERT INTO usuarios VALUES ('"+customer.getId()+"','"+customer.getName()+"');");
                ResultSet rs = pst.executeQuery();
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
            return customer;
        }
        else{
            System.out.println("El usuario ya se encuentra dentro de la base de datos.");
            return null;
        }
    }*/

    //Codigo que inserta en la base de datos los descuentos de cada cliente
    /*public Descuento addDescuento(Customer customer,Descuento descuento) {
        // iniciamos la conexion con la base de datos
        Connection con = ConnectionDAO.getInstance().getConnection();
        try {
            // Añadimos el descuento a la tabla de descuentos con el formato previamente establecido.
            // El primer valor será el ID del dueño del descuento seguido por el descuento.
            PreparedStatement pst = con.prepareStatement("INSERT INTO descuentos VALUES ('" + customer.getId() +"','"+descuento.getComercio() + "','" + descuento.getFechaIn() +"','"
                    +descuento.getFechaFin()+"','"+ descuento.getTipo()+"','"+descuento.getValor()+"','"+descuento.getCodigo()+"');");
            ResultSet rs = pst.executeQuery();
            return descuento;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    // Metodo para extraccion de los descuentos de un customer.
    public ArrayList<Descuento> getDescuentos(ArrayList<Descuento> lista, Customer customer){
        //Como debemos extraer descuentos, necesitamos conexion a la base de datos, por lo que tenemos que gnerar una conexion
        Connection con = ConnectionDAO.getInstance().getConnection();
        //Si la lista que debemos actualizar no existe o si su tamaño es cero, la volvemos a crear
        if(lista == null || lista.size() == 0)
        {
            lista = new ArrayList<Descuento>();
        }
        //Ejecutamos la query que obtiene los descuentos asociados a un usuario
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM descuentos WHERE usuario= '"+customer.getId()+ "';");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                // Estos descuentos son añadidos a la lista que se ha pasado por parámetro.
                lista.add(new Descuento(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }*/


    //public ArrayList<Descuento> getDescuentos (){

    //}

}