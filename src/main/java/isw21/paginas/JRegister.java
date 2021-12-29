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
        String host = PropertiesISW.getInstance().getProperty("host");
        int port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
        Logger.getRootLogger().info("Host: "+host+" port"+port);
        Client cliente=new Client(host, port);
        //setSize(450,350);
        //this.setColor(BLUE);
        this.setLayout(new BorderLayout());
        Font fuente = new Font("Serif", 0, 15);
        Font fuente2 = new Font("Serif", 0, 12);

//GRÁFICO
        //NORTE

        JPanel pnlNorte = new JPanel();
        pnlNorte.setPreferredSize(new Dimension(350, 100));
        JLabel lblTitulo = new JLabel("Register");
        lblTitulo.setFont(fuente);
        pnlNorte.setAlignmentX(lblTitulo.CENTER_ALIGNMENT);
        pnlNorte.add(lblTitulo);
        //pnlNorte.setBorder(BorderFactory.createEtchedBorder());



        //CENTRO

        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridLayout(3,2));



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



        //pnlCentro.setBorder(BorderFactory.createEtchedBorder());




        //SUR

        JPanel pnlSur = new JPanel();
        pnlSur.setPreferredSize(new Dimension(350, 100));
        JButton btnRegister = new JButton("Register");
        btnRegister.setForeground(Color.BLUE);  //new Color(254, 155, 32)
        //pnlSur.add(new JLabel( "prueba",JLabel.CENTER ),BorderLayout.CENTER );

        pnlSur.add(btnRegister);
        //pnlSur.setBorder(BorderFactory.createEtchedBorder());


//FUNCIONES
        btnRegister.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                cliente.setContext("/addNewUser");
                cliente.setNombre(txtPassword.getText());
                cliente.setId(txtUser.getText());
                cliente.run(cliente);
                if (cliente.getIdentification()){
                    System.out.println("Se ha añadido el usuario a la base de datos");
                    setVisible(false);
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
                txtMail.setText("");
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
                txtPassword.setText("");
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
                    btnRegister.requestFocus();
            }
        });

        this.add(pnlNorte, BorderLayout.NORTH);
        this.add(pnlCentro, BorderLayout.CENTER);
        this.add(pnlSur, BorderLayout.SOUTH);


        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(500, 100);

    }
}
