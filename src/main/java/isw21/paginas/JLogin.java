package main.java.isw21.paginas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;



import main.java.isw21.client.Client;
import main.java.isw21.configuration.PropertiesISW;
import main.java.isw21.paginas.JRegister;
import org.apache.log4j.Logger;
import main.java.isw21.client.Client;
import main.java.isw21.configuration.PropertiesISW;
import main.java.isw21.message.Message;
import main.java.isw21.domain.Customer;
import org.apache.log4j.Logger;
import main.java.isw21.paginas.JPrincipal;

public class JLogin extends JFrame
{
    public Boolean logCorrect;
    public Customer customer;

    public JLogin()
    {

        //Creamos la conexion con el servidor

        String host = PropertiesISW.getInstance().getProperty("host");
        int port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
        Logger.getRootLogger().info("Host: "+host+" port"+port);
        Client cliente=new Client(host, port);
        
        
        setSize(450,350);
        //this.setColor(BLUE);
        this.setLayout(new BorderLayout());
	Font fuente = new Font("Serif", 0, 15);
	Font fuente1 = new Font("Serif", 1, 18);
	Font fuente2 = new Font("Serif", 0, 12);
	Font fuente3 = new Font("Serif", 0, 30);

//GRÁFICO
        //NORTE

        JPanel pnlNorte = new JPanel();
        //pnlNorte.setPreferredSize(new Dimension(350, 100));
	pnlNorte.setBackground(new Color(112,157,119));
        JLabel lblTitulo = new JLabel("Login");
        lblTitulo.setFont(fuente3);
	lblTitulo.setForeground(Color.white);
        pnlNorte.setAlignmentX(lblTitulo.CENTER_ALIGNMENT);
        pnlNorte.add(lblTitulo);
        //pnlNorte.setBorder(BorderFactory.createEtchedBorder());



        //CENTRO

        JPanel pnlCentro = new JPanel();
        JPanel pnlCentro1 = new JPanel();
        JPanel pnlCentro2 = new JPanel();



        pnlCentro1.setLayout(new GridLayout(3,3));

        pnlCentro2.setLayout(new FlowLayout());



        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(fuente);
        //lblUser.setBorder(BorderFactory.createEtchedBorder());


        JTextField txtUser = new JTextField( 10);
        txtUser.setFont(fuente);
        txtUser.setForeground(new Color(148, 148, 148));

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(fuente);
        JPasswordField txtPassword = new JPasswordField( 10);
        txtPassword.setFont(fuente);
        txtPassword.setForeground(new Color(148, 148, 148));

        JLabel l = new JLabel("");
        
        //l.setBorder(BorderFactory.createEtchedBorder());
        JLabel lblRegister = new JLabel("¿Don´t have an account?");
        lblRegister.setFont(fuente2);
        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(fuente);


        pnlCentro1.add(lblUser);
        pnlCentro1.add(txtUser);
        pnlCentro1.add(lblPassword);
        pnlCentro1.add(txtPassword);
        pnlCentro1.add(l);
        pnlCentro1.add(pnlCentro2);

       
        pnlCentro2.add(lblRegister);
        pnlCentro2.add(btnRegister);


        pnlCentro.add(pnlCentro1);

        pnlCentro.setAlignmentX(pnlCentro1.CENTER_ALIGNMENT);

        //pnlCentro.setBorder(BorderFactory.createEtchedBorder());
        //pnlCentro1.setBorder(BorderFactory.createEtchedBorder());
        //pnlCentro2.setBorder(BorderFactory.createEtchedBorder());






        //SUR

        JPanel pnlSur = new JPanel();
        pnlSur.setPreferredSize(new Dimension(80, 60));
        JButton btnLogin = new JButton("Login");
        btnLogin.setForeground(new Color(17,90,29));  //new Color(254, 155, 32)	
	btnLogin.setFont(fuente1);
	btnLogin.setPreferredSize(new Dimension(90, 40));
        pnlSur.add(btnLogin);
        //pnlSur.setBorder(BorderFactory.createEtchedBorder());
	JPanel pnlEste = new JPanel();

	JPanel pnlOeste = new JPanel();
	pnlOeste.setPreferredSize(new Dimension(60, 60));

//FUNCIONES
        btnRegister.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JRegister register = new JRegister();
                setVisible(false);
            }
        });
         //Para que funcione al darle enter en Login:
	btnLogin.addKeyListener(new java.awt.event.KeyAdapter() 
	{
            public void keyPressed(java.awt.event.KeyEvent e) 
            {

                // Para identificarse de manera correcta, se debe introducir correctamente el nombre y la contraseña de un usuario
                //El servidor será el encargado de hacer la comprobación con la base de datos. Po lo tanto deberemos comunicarnos con el
                // Para la comunicacion, utilizaremos el contexto de getAcces. En el mensaje tambien se especificará con que nombre y que contraseña
                // se esta intentando acceder
                cliente.setContext("/getAccess");
                cliente.setNombre(txtPassword.getText());
                cliente.setId(txtUser.getText());
                //Mandamos la comunicacion al servidor
                cliente.run(cliente);
                //Si la comuncacion es corrcta
                if (cliente.getIdentification()){
                    logCorrect=true;
                    //Iniciamos sesion con el customer introducido por el usuario y pasamos a la pestaña de inicio
                    customer= new Customer(txtUser.getText(),txtPassword.getText());
                    System.out.println("Se ha logeado");
                    JInicio inicio= new JInicio(customer,cliente);
                    setVisible(false);

                }
                else{
                    setVisible(true);
                }
            };
         });
         
		//Al darle click:
        btnLogin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Misma funcionalidad que en el caso previo
                cliente.setContext("/getAccess");
                cliente.setNombre(txtPassword.getText());
                cliente.setId(txtUser.getText());
                cliente.run(cliente);
                if (cliente.getIdentification()){
                    logCorrect=true;
                    customer= new Customer(txtUser.getText(),txtPassword.getText());
                    System.out.println("Se ha logeado");
                    JInicio inicio= new JInicio(customer,cliente);
                    setVisible(false);
                }
                else{
                    setVisible(true);
                }

            };
        });

        
        //Funciones de accesibilidad en la pestaña
        txtUser.addMouseListener(new MouseAdapter()
        {
            //@Override
            public void mouseClicked(MouseEvent e)
            {
                txtUser.setText("");
            }
        });

        txtUser.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) //valor de key: enter
                    txtPassword.requestFocus();
   
            }
        });

        txtPassword.addMouseListener(new MouseAdapter()
        {
            //@Override
            public void mouseClicked(MouseEvent e)
            {
                txtPassword.setText("");
            }
        });
        txtPassword.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) //valor de key: enter
                    btnLogin.requestFocus();
            }
        });

        this.add(pnlNorte, BorderLayout.NORTH);
        this.add(pnlCentro, BorderLayout.CENTER);
        this.add(pnlSur, BorderLayout.SOUTH);
	this.add(pnlEste, BorderLayout.EAST);
	this.add(pnlOeste, BorderLayout.WEST);


    this.pack();
    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            JPrincipal jprinc = new JPrincipal();

        }
    });
    this.setVisible(true);
    this.setLocation(480, 200);

    }

    public Customer getCustomer() {
        return customer;
    }

    public Boolean getLogCorrect() {
        return logCorrect;
    }
}

