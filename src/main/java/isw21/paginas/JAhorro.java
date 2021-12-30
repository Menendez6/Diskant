package main.java.isw21.paginas;

import main.java.isw21.client.Client;
import main.java.isw21.domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana en la que se muestra el ahorro del usuario, dividido por el tipo de descuentos.
 */
public class JAhorro extends JFrame {
    Customer customer;
    Client cliente;

    public JAhorro(Customer customer, Client cliente)
    {
        this.customer=customer;
        this.cliente=cliente;

        setSize(600,600);
        this.setLayout(new BorderLayout());
        Font fuente = new Font("Serif", 0, 15);
        Font fuente1 = new Font("Serif", 1, 18);
        Font fuente2 = new Font("Serif", 0, 30);

//GRÁFICO
        //NORTE
        JPanel pnlNorte = new JPanel();
        pnlNorte.setBackground(new Color(255,255,255));
        pnlNorte.setLayout(new GridLayout(1,3));
        JLabel l5 = new JLabel();
        ImageIcon imagen1 = new ImageIcon("src/main/java/isw21/media/LogoDiskAnt.jpeg");
        l5.setIcon(new ImageIcon(imagen1.getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH)));
        JLabel l6 = new JLabel("INFORME DE AHORRO");
        l6.setFont(fuente);
        JLabel l7 = new JLabel();
        pnlNorte.setPreferredSize(new Dimension(600, 50));

        pnlNorte.add(l5);
        pnlNorte.add(l6);
        pnlNorte.add(l7);


        //CENTRO
        JPanel pnlCentro = new JPanel();
        pnlCentro.setBackground(new Color(174,200,178));
        pnlCentro.setLayout(new GridLayout(3,1));

        JLabel titulo = new JLabel("He ahorrado: ", JLabel.CENTER);
        titulo.setFont(fuente1);
        pnlCentro.add(titulo);

        JPanel pnlCentroCentro = new JPanel();
        pnlCentroCentro.setBackground(new Color(174,200,178));
        pnlCentroCentro.setLayout(new GridLayout(1,3));
        JLabel desc = new JLabel("En descuentos: ", JLabel.CENTER);
        desc.setFont(fuente1);
        JLabel porc = new JLabel("En porcentajes: ", JLabel.CENTER);
        porc.setFont(fuente1);
        JLabel check = new JLabel("En cheques: ", JLabel.CENTER);
        check.setFont(fuente1);
        pnlCentroCentro.add(desc);
        pnlCentroCentro.add(porc);
        pnlCentroCentro.add(check);

        pnlCentro.add(pnlCentroCentro);

        double[] ahorro = customer.getAhorrado();

        JPanel pnlCentroBajo = new JPanel();
        pnlCentroBajo.setBackground(new Color(174,200,178));
        pnlCentroBajo.setLayout(new GridLayout(1,3));
        JLabel desc1 = new JLabel(String.valueOf(ahorro[0]) + "€", JLabel.CENTER);
        desc1.setFont(fuente1);
        JLabel porc1 = new JLabel(String.valueOf(ahorro[1]) + "%", JLabel.CENTER);
        porc1.setFont(fuente1);
        JLabel check1 = new JLabel(String.valueOf(ahorro[2]) + "€" ,JLabel.CENTER);
        check1.setFont(fuente1);
        pnlCentroBajo.add(desc1);
        pnlCentroBajo.add(porc1);
        pnlCentroBajo.add(check1);

        pnlCentro.add(pnlCentroBajo);


        //JPanel pnlCentroCentro = new JPanel();
        //pnlCentroCentro.setLayout(new FlowLayout());





        //pnlCentroCentro.add(btnLapiz);

        JLabel l9 = new JLabel();
        JLabel l8 = new JLabel();
        //pnlCentro.add(l9);
        //pnlCentro.add(pnlCentroCentro);
        //pnlCentro.add(l8);


        //SUR
        JPanel pnlSur = new JPanel();
        pnlSur.setLayout(new GridLayout(1, 5));

        JLabel lblMas = new JLabel();

        JLabel lblLupa = new JLabel();

        JButton btnHome = new JButton();
        btnHome.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ImageIcon imagenHome = new ImageIcon("src/main/java/isw21/media/Home.png");
        btnHome.setIcon(new ImageIcon(imagenHome.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        JLabel lblCorazon = new JLabel();

        JLabel lblMiPerfil= new JLabel();

        pnlSur.add(lblMas);
        pnlSur.add(lblLupa);
        pnlSur.add(btnHome);
        pnlSur.add(lblCorazon);
        pnlSur.add(lblMiPerfil);

        //LADOS
        JPanel pnlEste = new JPanel();
        pnlEste.setPreferredSize(new Dimension(40, 60));
        pnlEste.setBackground(new Color(174,200,178));
        JPanel pnlOeste = new JPanel();
        pnlOeste.setPreferredSize(new Dimension(40, 60));
        pnlOeste.setBackground(new Color(174,200,178));

        this.add(pnlNorte, BorderLayout.NORTH);
        this.add(pnlCentro, BorderLayout.CENTER);
        this.add(pnlSur, BorderLayout.SOUTH);
        this.add(pnlEste, BorderLayout.EAST);
        this.add(pnlOeste, BorderLayout.WEST);

//FUNCIONES


        btnHome.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //JInicio inicio = new JInicio();
                JInicio inicio = new JInicio(customer, cliente);
                setVisible(false);
            }
        });
    /*btnMiPerfil.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        JMiPerfil perfil = new JMiPerfil(customer,cliente, ofertas);
        setVisible(false);
      }
    });*/

        //this.pack();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LO DE ABAJO VA EN EL CODIGO
        /*this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JPrincipal jprinc = new JPrincipal();

            }
        });*/
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocation(250, 100);

    }
}
