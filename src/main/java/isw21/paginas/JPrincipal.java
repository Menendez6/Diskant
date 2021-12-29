package main.java.isw21.paginas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import main.java.isw21.paginas.*;

public class JPrincipal extends JFrame
{
    public static void main(String args[])
    {
        new JPrincipal();
    }
    public JPrincipal()
    {
        setSize(900,600);

        JLabel lblBienvenida= new JLabel("Bienvenido a DiskAnt");
        Font fuente = new Font("Serif", 0, 35);
        lblBienvenida.setFont(fuente);
        JPanel pnlNorte= new JPanel();
        pnlNorte.add(lblBienvenida);

        JPanel pnlCentro = new JPanel();
        JButton btnLogin = new JButton("Log in");
        //btn_login.setPreferredSize(new Dimension(200, 50));
        pnlCentro.add(btnLogin);

        btnLogin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JLogin login = new JLogin();
            };
        });

        JButton btnRegister = new JButton("Register");
        pnlCentro.add(btnRegister);

        btnRegister.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JRegister register = new JRegister();
            }
        });
        this.setLayout(new GridLayout(2,1));
        this.add(pnlNorte);

        this.add(pnlCentro);
        this.pack();
        this.setSize(500,400);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(250, 100);
    }


}
