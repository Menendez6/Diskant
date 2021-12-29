// Descripicion del entorno grafico de la pestaña de registrar
package main.java.isw21.paginas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

import main.java.isw21.client.Client;
import main.java.isw21.configuration.PropertiesISW;
import main.java.isw21.message.Message;
import main.java.isw21.domain.Customer;
import org.apache.log4j.Logger;

public class JRegister extends JFrame
{
    public static void main(String args[])
    {
        new JRegister();
    }

    public JRegister()
    {
        // La ventana de register debe tener conectividad con la base de datos para podr añadir los usuarios
        String host = PropertiesISW.getInstance().getProperty("host");
        int port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
        Logger.getRootLogger().info("Host: "+host+" port"+port);
        Client cliente=new Client(host, port);
        
	setSize(450,200);
        this.setLayout(new BorderLayout());
	Font fuente = new Font("Serif", 0, 15);
	Font fuente1 = new Font("Serif", 1, 18);
	Font fuente3 = new Font("Serif", 0, 30);

//GRÁFICO
        //NORTE

        JPanel pnlNorte = new JPanel();
	pnlNorte.setBackground(new Color(112,157,119));
        JLabel lblTitulo = new JLabel("Register");
	lblTitulo.setForeground(Color.white);
	lblTitulo.setFont(fuente3);
	pnlNorte.setAlignmentX(lblTitulo.CENTER_ALIGNMENT);
	pnlNorte.add(lblTitulo);
        //pnlNorte.setBorder(BorderFactory.createEtchedBorder());



        //CENTRO

        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridLayout(4,2));



        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(fuente);
        //lblUser.setBorder(BorderFactory.createEtchedBorder());


        JTextField txtUser = new JTextField("Type your username", 10);
        txtUser.setFont(fuente);
        txtUser.setForeground(new Color(148, 148, 148));


        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(fuente);
        JTextField txtPassword = new JTextField("Type your password", 10);
        txtPassword.setFont(fuente);
        txtPassword.setForeground(new Color(148, 148, 148));
	
	JLabel lblPassword2 = new JLabel("Confirm Password");
	lblPassword2.setFont(fuente);
	JTextField txtPassword2 = new JTextField("Type your password", 10);
	txtPassword2.setFont(fuente);
	txtPassword2.setForeground(new Color(148, 148, 148));


        JLabel lblMail = new JLabel("Email");
        lblMail.setFont(fuente);
        JTextField txtMail = new JTextField("Type your email", 10);
        txtMail.setFont(fuente);
        txtMail.setForeground(new Color(148, 148, 148));

        pnlCentro.add(lblUser);
        pnlCentro.add(txtUser);
        pnlCentro.add(lblMail);
        pnlCentro.add(txtMail);
        pnlCentro.add(lblPassword);
        pnlCentro.add(txtPassword);
	pnlCentro.add(lblPassword2);
	pnlCentro.add(txtPassword2);



        //pnlCentro.setBorder(BorderFactory.createEtchedBorder());




        //SUR

        JPanel pnlSur = new JPanel();
        pnlSur.setPreferredSize(new Dimension(80, 60));
        JButton btnRegister = new JButton("Register");
        btnRegister.setForeground(new Color(17,90,29));
        btnRegister.setPreferredSize(new Dimension(90, 40));
        pnlSur.add(btnRegister);
        //pnlSur.setBorder(BorderFactory.createEtchedBorder());
	
	//LADOS
	JPanel pnlEste = new JPanel();

	JPanel pnlOeste = new JPanel();
	pnlOeste.setPreferredSize(new Dimension(40, 60));


//FUNCIONES
        btnRegister.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Utilizamos la verificacion de contraseña y si coinciden, se manda el el usuario a añadir a la base de datos
                // guardando los parametros introducidos por usuario.
	    	if(txtPassword.getText().equals(txtPassword2.getText()))
                {
                    //Envio del mensaje a la base de datos en el contexto de añadir user
			cliente.setContext("/addNewUser");
			cliente.setNombre(txtPassword.getText());
			cliente.setId(txtUser.getText());
            // Con este contexto, como se puede ver en el servidor, solo es encesario saber el nombre y la contraseña. En un futuro se añadiran otros campos.
                    // Como por ejemplo numero de telefono o email.
			cliente.run(cliente);
            //Si el servidor, en su mensaje de vuelta, nos devuelve true, indicará que que el usuario se ha añadido correctamente a la base de datos
			if (cliente.getIdentification()){
			    System.out.println("Se ha añadido el usuario a la base de datos");
                // Una vez introducido en la base de datos, tendrá que volver a la pantalla principal e iniciar sesion
			    JPrincipal jp = new JPrincipal();
			    setVisible(false);
			}
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Passwords do not match. Retry");
                    txtPassword.requestFocus();
                    txtPassword.setText("");
                    txtPassword2.setText("");
                }
                
		
		

            };
        });
	
	btnRegister.addKeyListener(new java.awt.event.KeyAdapter()
        {
           public void keyPressed(java.awt.event.KeyEvent e) 
            {
                if(txtPassword.getText().equals(txtPassword2.getText()))
                {
                    	cliente.setContext("/addNewUser");
			cliente.setNombre(txtPassword.getText());
			cliente.setId(txtUser.getText());
			cliente.run(cliente);
			if (cliente.getIdentification()){
			    System.out.println("Se ha añadido el usuario a la base de datos");
			    JPrincipal jp = new JPrincipal();
			    setVisible(false);
			}
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Passwords do not match. Retry");
                    txtPassword.requestFocus();
                    txtPassword.setText("");
                    txtPassword2.setText("");
                }

		
		
		
            };
        });

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
                    txtMail.requestFocus();
            }
        });


        txtMail.addMouseListener(new MouseAdapter()
        {
            //@Override
            public void mouseClicked(MouseEvent e)
            {
                txtMail.setText("");
            }
        });

        txtMail.addKeyListener(new KeyAdapter()
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
				txtPassword2.requestFocus();
		}
	});
	txtPassword2.addMouseListener(new MouseAdapter()
	{
		//@Override
		public void mouseClicked(MouseEvent e)
		{
			txtPassword2.setText("");
		}
	});
	txtPassword2.addKeyListener(new KeyAdapter()
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode()==KeyEvent.VK_ENTER) //valor de key: enter
				btnRegister.requestFocus();
		}
	});

        this.add(pnlNorte, BorderLayout.NORTH);
        this.add(pnlCentro, BorderLayout.CENTER);
        this.add(pnlSur, BorderLayout.SOUTH);
	this.add(pnlEste, BorderLayout.EAST);
	this.add(pnlOeste, BorderLayout.WEST);


        //this.pack();
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
}
