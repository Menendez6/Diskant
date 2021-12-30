
package main.java.isw21.paginas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Ventana en la que el usuario puede valorar la aplicación con estrellas.
 * @version 0.3
 */

public class JOpinion extends JFrame
{
	public static void main(String args[])
	{
		new JOpinion();
	}
	public JOpinion()
	{
		setSize(450,250);
		this.setLayout(new BorderLayout());
		Font fuente3 = new Font("Serif", 0, 30);

//GRAFICO
	//NORTE
		JPanel pnlNorte = new JPanel();
		pnlNorte.setBackground(new Color(112,157,119));
		JLabel lblTitulo = new JLabel("Valora");
		lblTitulo.setFont(fuente3);
		lblTitulo.setForeground(Color.white);
		pnlNorte.setAlignmentX(lblTitulo.CENTER_ALIGNMENT);
		pnlNorte.add(lblTitulo);

	//CENTRO
		JPanel pnlCentro = new JPanel();
		pnlCentro.setLayout(new GridLayout(1,5));
		//pnlCentro.setPreferredSize(new Dimension(450, 100));

		ImageIcon imagenGris = new ImageIcon("src/main/java/isw21/media/Gris.jpg");
		ImageIcon imagenAmarillo = new ImageIcon("src/main/java/isw21/media/Amarillo.jpg");
		JButton btn1 = new JButton();
		JButton btn2 = new JButton();
		JButton btn3 = new JButton();
		JButton btn4 = new JButton();
		JButton btn5 = new JButton();
    	btn1.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
    	btn2.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
    	btn3.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
    	btn4.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
    	btn5.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

    //SUR
    	JPanel pnlSur = new JPanel();
    	JButton btnEnviar = new JButton("enviar");
    	btnEnviar.setForeground(new Color(17,90,29));
        //btnEnviar.setPreferredSize(new Dimension(90, 40));
		pnlSur.setBackground(new Color(112,157,119));
        pnlSur.add(btnEnviar);

//FUNCIONES
	    btn1.addActionListener(new ActionListener()
	    {
	   		int i = 1; 
			public void actionPerformed(ActionEvent e)
			{
				i=i+1;
				if(i%2==0) //par
					btn1.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
				else
					{btn1.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn2.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn3.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn4.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn5.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));}

			}
		});

		btn2.addActionListener(new ActionListener()
	    {
	   		int i = 1; 
			public void actionPerformed(ActionEvent e)
			{
				i=i+1;
				if(i%2==0) //par
					{btn1.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn2.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));}
				else
					{btn2.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn3.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn4.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn5.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));}

			}
		});

		btn3.addActionListener(new ActionListener()
	    {
	   		int i = 1; 
			public void actionPerformed(ActionEvent e)
			{
				i=i+1;
				if(i%2==0) //par
					{btn1.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn2.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn3.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));}
				else
					{btn3.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn4.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn5.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));}
					

			}
		});

		btn4.addActionListener(new ActionListener()
	    {
	   		int i = 1; 
			public void actionPerformed(ActionEvent e)
			{
				i=i+1;
				if(i%2==0) //par
					{btn1.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn2.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn3.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn4.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));}
				else
					{btn4.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn5.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));}
					

			}
		});

		btn5.addActionListener(new ActionListener()
	    {
	   		int i = 1; 
			public void actionPerformed(ActionEvent e)
			{
				i=i+1;
				if(i%2==0) //par
					{btn1.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn2.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn3.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn4.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					btn5.setIcon(new ImageIcon(imagenAmarillo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));}
				else
					btn5.setIcon(new ImageIcon(imagenGris.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					

			}
		});
		btnEnviar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});

	    pnlCentro.add(btn1);
	    pnlCentro.add(btn2);
	    pnlCentro.add(btn3);
	    pnlCentro.add(btn4);
	    pnlCentro.add(btn5);

	    this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlNorte, BorderLayout.NORTH);
		this.add(pnlSur, BorderLayout.SOUTH);

		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);

            }
        });
		this.setVisible(true); 
		this.setLocation(550, 250);
	}

	
}
