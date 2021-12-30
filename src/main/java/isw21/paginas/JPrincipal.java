package main.java.isw21.paginas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import main.java.isw21.paginas.*;

/**
 * Ventana que salta al abrir la aplicación. Desde ella puedes ir a hacer login o register.
 * @version 0.1
 */
public class JPrincipal extends JFrame
{
	public static void main(String args[])
	{
		new JPrincipal();
	}
	public JPrincipal()
	{
		setSize(500,400);
		this.setLayout(new BorderLayout());
		Font fuente = new Font("Serif", 1, 30);

        Font fuente2 = new Font("Serif", 0, 18);
        this.setBackground(new Color(17,90,29));

	//NORTE	
		JPanel pnlNorte = new JPanel();
		

		JLabel lblBienvenida = new JLabel("Bienvenido a DiskAnt");

        lblBienvenida.setFont(fuente);
		pnlNorte.add(lblBienvenida);

	

    //CENTRO
		JPanel pnlCentro = new JPanel();

		pnlCentro.setBackground(new Color(112,157,119));
		pnlCentro.setLayout(new GridLayout(4,3));
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
		JLabel l4 = new JLabel();
		JLabel l5 = new JLabel();
		JLabel l6 = new JLabel();
		JLabel l7 = new JLabel();
		

		JButton btnLogin = new JButton("Log in");
		btnLogin.setFont(fuente2);


		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(fuente2);

		pnlCentro.add(l1);
		pnlCentro.add(l2);
		pnlCentro.add(l3);
		pnlCentro.add(l4);
		pnlCentro.add(btnLogin);
		pnlCentro.add(l5);
		pnlCentro.add(l6);
		pnlCentro.add(btnRegister);
		pnlCentro.add(l7);

	//SUR
		JPanel pnlSur = new JPanel();
        JLabel lblLogo= new JLabel();
		ImageIcon imagen = new ImageIcon("src/main/java/isw21/media/LogoDiskAnt.jpeg");
		lblLogo.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH)));

        pnlSur.setBackground(new Color(112,157,119));
        pnlSur.add(lblLogo);
	
	//FUNCIONES
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JLogin login = new JLogin();
				setVisible(false);
			};
		});
		btnRegister.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JRegister register = new JRegister();
				setVisible(false);
			}
		});
		

	
		this.add(pnlNorte, BorderLayout.NORTH);
		this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlSur, BorderLayout.SOUTH);


		//this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setResizable(false);
		this.setVisible(true); 
		this.setLocation(500, 100);
	}

	
}

