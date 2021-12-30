package main.java.isw21.paginas;
import main.java.isw21.client.Client;
import main.java.isw21.descuentos.Oferta;
import main.java.isw21.domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;


import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Ventana en la que se muestra la información del perfil del usuario, con varios botones para consultar varias cosas, entre ellas la privacidad de la aplicación
 * @version 0.3
 */

public class JMiPerfil extends JFrame
{
    //Este corchete public static no se pone en github 
    //public static void main(String args[])
    //{
    //    new JMiPerfil();
        
    //}
  
    /*Customer customer;
    Client cliente;*/

    //public JMiPerfil()
    public JMiPerfil(Customer customer, Client cliente, ArrayList<Oferta> ofertas)
    {
        setSize(600,600);
        this.setLayout(new BorderLayout());
        Font fuente = new Font("Serif", 0, 15);
        Font fuente1 = new Font("Serif", 1, 18);
        Font fuente3 = new Font("Serif", 0, 30);

        //GRÁFICO
        //NORTE
        JPanel pnlNorte = new JPanel();
        pnlNorte.setBackground(new Color(255,255,255));
        pnlNorte.setLayout(new GridLayout(1,3));
        JLabel l5 = new JLabel();
        ImageIcon imagen1 = new ImageIcon("src/main/java/isw21/media/LogoDiskAnt.jpeg");
        l5.setIcon(new ImageIcon(imagen1.getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH)));
        JLabel l6 = new JLabel("Mi Perfil");
        JLabel l7 = new JLabel();
        pnlNorte.setPreferredSize(new Dimension(600, 50));

        pnlNorte.add(l5);
        pnlNorte.add(l6);
        pnlNorte.add(l7);

        //CENTRO
        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new BorderLayout());
        pnlCentro.setBackground(new Color(174,200,178));


        JPanel pnlCentroNorte = new JPanel();
        pnlCentroNorte.setBackground(new Color(174,200,178));
        pnlCentroNorte.setLayout(new FlowLayout());

        JLabel lblCara = new JLabel();
        ImageIcon imagenCara= new ImageIcon("src/main/java/isw21/media/Cara.png");
        lblCara.setIcon(new ImageIcon(imagenCara.getImage().getScaledInstance(110, 80, Image.SCALE_SMOOTH)));
        lblCara.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));

        JLabel lblEspacio = new JLabel();
        lblEspacio.setPreferredSize(new Dimension(20, 130));


        JLabel lblInfo = new JLabel(customer.getUsuario());
        lblInfo.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
        lblInfo.setPreferredSize(new Dimension(200, 110));
        lblInfo.setBackground(new Color(17,90,29));

        pnlCentroNorte.add(lblCara);
        pnlCentroNorte.add(lblEspacio);
        pnlCentroNorte.add(lblInfo);

        JPanel pnlCentroCentro = new JPanel();
        pnlCentroCentro.setLayout(new FlowLayout());
        pnlCentroCentro.setBackground(new Color(174,200,178));

      JButton btnCuenta = new JButton("Mi cuenta");
      btnCuenta.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
      btnCuenta.setPreferredSize(new Dimension(500, 30));
      btnCuenta.setBackground(new Color(237,237,237));
      btnCuenta.setForeground(new Color(150,150,150));

      JButton btnConfiguracion = new JButton("Configuracion");
      btnConfiguracion.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
      btnConfiguracion.setPreferredSize(new Dimension(500, 30));
      btnConfiguracion.setBackground(new Color(237,237,237));
      btnConfiguracion.setForeground(new Color(150,150,150));

      JButton btnAyuda = new JButton("Ayuda");
      btnAyuda.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
      btnAyuda.setPreferredSize(new Dimension(500, 30));
      btnAyuda.setBackground(new Color(237,237,237));
      btnAyuda.setForeground(new Color(150,150,150));

      JButton btnPrivacidad = new JButton("Privacidad");
      btnPrivacidad.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
      btnPrivacidad.setPreferredSize(new Dimension(500, 30));
      btnPrivacidad.setBackground(new Color(237,237,237));
      btnPrivacidad.setForeground(new Color(150,150,150));

      JButton btnSesion = new JButton("Cerrar sesion");
      btnSesion.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
      btnSesion.setPreferredSize(new Dimension(500, 30));
      btnSesion.setBackground(new Color(237,237,237));
      btnSesion.setForeground(new Color(150,150,150));

      JButton btnInvitar = new JButton("Invitar");
      btnInvitar.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
      btnInvitar.setPreferredSize(new Dimension(500, 30));
      btnInvitar.setBackground(new Color(237,237,237));
      btnInvitar.setForeground(new Color(150,150,150));

      JButton btnOpinion = new JButton("¡Danos tu opinion!");
      btnOpinion.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
      btnOpinion.setPreferredSize(new Dimension(500, 30));
      btnOpinion.setBackground(new Color(237,237,237));
      btnOpinion.setForeground(new Color(150,150,150));



        pnlCentroCentro.add(btnCuenta);
        pnlCentroCentro.add(btnConfiguracion);
        pnlCentroCentro.add(btnAyuda);
        pnlCentroCentro.add(btnPrivacidad);
        pnlCentroCentro.add(btnSesion);
        pnlCentroCentro.add(btnInvitar);
        pnlCentroCentro.add(btnOpinion);


        pnlCentro.add(pnlCentroNorte, BorderLayout.NORTH);
        pnlCentro.add(pnlCentroCentro, BorderLayout.CENTER);


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

        /*btnMas.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JAgregar agregar = new JAgregar(customer, cliente, ofertas);
                setVisible(false);
            }
        });
        */
        btnOpinion.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JOpinion opinon = new JOpinion();
            }
        });
        btnAyuda.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            JOptionPane.showMessageDialog(null, "Para cualquier pregunta, escribenos a diskant@gmail.com");
          }
        });
        btnPrivacidad.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {

            String s = JMiPerfil.importTexto();
            JOptionPane.showMessageDialog(null,s);
          }
        });
        btnSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                new JPrincipal();
            }
        });
        btnInvitar.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            int min_val = 10000000;
            int max_val = 99999999;
            Random ran = new Random();
            int x = ran.nextInt(max_val) + min_val;
            JOptionPane.showMessageDialog(null,"El codigo para invitar a otra persona es: "+x);
          }
        });

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
     public static String importTexto()
        {
          Collection<String> texto = new ArrayList<String>();
          String textFinal = new String();
          try
          {
            FileReader fr = new FileReader("src/main/java/isw21/media/Privacidad.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea = null;
            while((linea = br.readLine()) != null)
            {
              texto.add(linea);

            }
            textFinal= new String();
            for (String i: texto){
                textFinal =textFinal+i+"\n";
            }
            System.out.println(textFinal);
          }

          catch(IOException ioe)
          {
            ioe.printStackTrace();
          }
          return textFinal;
        }
}
