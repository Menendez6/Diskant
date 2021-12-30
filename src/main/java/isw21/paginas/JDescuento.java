//Descripción de la clase JDescuento: Encargada del entorno gráfico del añadido de un descuento
package main.java.isw21.paginas;

import main.java.isw21.client.Client;
import main.java.isw21.descuentos.OfertaFactory;
import main.java.isw21.domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.*;

import main.java.isw21.excepciones.PorcentajeException;
import main.java.isw21.tools.Checker;
import main.java.isw21.descuentos.Oferta;

/**
 * Ventana en la que se añade un descuento introduciendo el código, el comercio, la fecha de caducidad, el tipo y
 * el valor
 */
public class JDescuento extends JFrame
{
	Customer customer;
    Client cliente;

	// Siempre que una pantalla interactuca con la base de datos, debemos introducir el cliente como parámetro,
	// para poder tener la conexion entre cliente y servidor disponible.
	//Tambien es necesario conocer los descuentos asociados a la cuenta.

	// Por lo tanto, siempre que queramos añadir un descuento, deberemos introducir el cliente (conexión con servidor) y los descuentos
	public JDescuento(Customer customer, Client cliente, ArrayList<Oferta> ofertas)
	{
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE,50);

		this.customer=customer;
        this.cliente=cliente;
        OfertaFactory factoria = new OfertaFactory();
        
		setSize(450,300);

		Font fuente = new Font("Serif", 0, 15);
		Font fuente1 = new Font("Serif", 1, 18);
		Font fuente2 = new Font("Serif", 0, 12);
		Font fuente3 = new Font("Serif", 0, 30);

		this.setLayout(new BorderLayout());

	//NORTE
		JPanel pnlNorte = new JPanel();
		pnlNorte.setBackground(new Color(112,157,119));
		JLabel lblTitulo = new JLabel("Agregar nuevo descuento");
		lblTitulo.setFont(fuente3);
		lblTitulo.setForeground(Color.white);
		pnlNorte.setAlignmentX(lblTitulo.CENTER_ALIGNMENT);
		pnlNorte.add(lblTitulo);


	//CENTRO

		JPanel pnlCentro = new JPanel();
		pnlCentro.setLayout(new GridLayout(6,2));
		//pnlCentro.setLayout(new FlowLayout());

		JLabel lblEntidad = new JLabel("Entidad: ");
		JTextField txtEntidad = new JTextField();

		JPanel pnlFechaIni = new JPanel();
		pnlFechaIni.setLayout(new GridLayout(1,3));
		// Fecha
		String[] dias = new String[31];
		for (int i = 0; i < 31; i++) dias[i] = String.valueOf(i + 1);
		String[] meses = new String[12];
		for (int i = 0; i < 12; i++) meses[i] = String.valueOf(i + 1);
		String[] años = new String[40];
		for (int i = 0; i < 40; i++) años[i] = String.valueOf(i + 2010);

		String date = new SimpleDateFormat("dd/MM/YYYY").format(new Date());
		String str[]=date.split("/");

		JLabel lblInicio = new JLabel("Fecha inicio: ");
		//JTextField txtInicio = new JTextField();
		JComboBox<String> cbxDiaIni = new JComboBox<String>(dias);
		cbxDiaIni.setSelectedItem(str[0]);
		JComboBox<String> cbxMesIni = new JComboBox<String>(meses);
		cbxMesIni.setSelectedItem(str[1]);
		JComboBox<String> cbxAñoIni = new JComboBox<String>(años);
		pnlFechaIni.add(cbxDiaIni);
		cbxAñoIni.setSelectedItem(str[2]);
		pnlFechaIni.add(cbxMesIni);
		pnlFechaIni.add(cbxAñoIni);

		JPanel pnlFechaFin = new JPanel();
		pnlFechaFin.setLayout(new GridLayout(1,3));


		date = new SimpleDateFormat("dd/MM/YYYY").format(cal.getTime());
		String str1[]=date.split("/");
		JLabel lblFin = new JLabel("Fecha fin: ");
		//JTextField txtFin = new JTextField();
		JComboBox<String> cbxDiaFin = new JComboBox<String>(dias);
		cbxDiaFin.setSelectedItem(str1[0]);
		JComboBox<String> cbxMesFin = new JComboBox<String>(meses);
		cbxMesFin.setSelectedItem(str1[1]);
		JComboBox<String> cbxAñoFin = new JComboBox<String>(años);
		cbxAñoFin.setSelectedItem(str1[2]);
		pnlFechaFin.add(cbxDiaFin);
		pnlFechaFin.add(cbxMesFin);
		pnlFechaFin.add(cbxAñoFin);

		JLabel lblTipo = new JLabel("Tipo: ");
		String[] tipos = {"Descuento", "Porcentaje", "ChequeRegalo"};
		JComboBox<String> cbxTipo = new JComboBox<String>(tipos);

		JLabel lblValor = new JLabel("Valor: ");
		JTextField txtValor = new JTextField();

		JLabel lblCodigo = new JLabel("Codigo:");
		JTextField txtCodigo = new JTextField();

	//SUR
		JPanel pnlSur = new JPanel();
		JButton btnCrear = new JButton("Crear Descuento");
		btnCrear.setForeground(new Color(37, 56, 40));
		btnCrear.setFont(fuente);
		btnCrear.addActionListener(new ActionListener()
		{
			// Método añadir descuento:


			public void actionPerformed(ActionEvent e)
            {
				String fechaIni = cbxDiaIni.getSelectedItem() + "/" + cbxMesIni.getSelectedItem() + "/" + cbxAñoIni.getSelectedItem();
				String fechaFin = cbxDiaFin.getSelectedItem() + "/" + cbxMesFin.getSelectedItem() + "/" + cbxAñoFin.getSelectedItem();
				Date dateIni;
				Date dateFin;
				try{
					dateIni = new SimpleDateFormat("dd/MM/yyyy").parse(fechaIni);
					dateFin = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFin);
				}
				catch(Exception exception){
					dateIni = new Date();
					dateFin = new Date();
				}

				//Varias excepciones como que si están vacíos te salga un aviso o que la fecha final no sea mayor a la inicial
				//o que el número que introduzcas del decuento no sea entero
				if(txtCodigo.getText().equals("") || txtEntidad.getText().equals("") || txtValor.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Introduce todos los campos");
					if(txtCodigo.getText().equals("")){
						txtCodigo.requestFocus();
					}
					else if(txtEntidad.getText().equals("")){
						txtEntidad.requestFocus();
					}
					else{
						txtValor.requestFocus();
					}
				}
				else if(dateIni.compareTo(dateFin) > 0 ){
					JOptionPane.showMessageDialog(null,"Introduce una fecha de inicio y una fecha de fin correctas");
					cbxDiaIni.requestFocus();
				}
				else if(Checker.onlyDigits(txtValor.getText(),txtValor.getText().length())!=true){
					JOptionPane.showMessageDialog(null,"El valor debe ser un numero entero positivo.");
					txtValor.requestFocus();
				}
				else{
					// Como debemos hacer un a conexión con el servidor para añadir el descuento
					// comenzamos a crear el mensaje de envio.
					// Establevcemos el contexto : añadir descuento
					try {

						cliente.setContext("/addDescuento");
						HashMap<String, Object> session = new HashMap<String, Object>();
						//Aquí creamos las fechas en función de lo introducido por el usuario, separando la información por barras
						//Formato dd/mm/aaaa
						//fechaIni = cbxDiaIni.getSelectedIndex() + "/" + cbxMesIni.getSelectedIndex() + "/" + cbxAñoIni.getSelectedIndex();
						//fechaFin = cbxDiaFin.getSelectedIndex() + "/" + cbxMesFin.getSelectedIndex() + "/" + cbxAñoFin.getSelectedIndex();
						//Creamos el descuento a añadir en función de los parámetros introducidos por el usuario en la pantalla.
						//Oferta oferta = new Oferta(txtEntidad.getText(), fechaIni,fechaFin,Integer.parseInt(txtValor.getText()),txtCodigo.getText());
						if(Integer.parseInt(txtValor.getText()) == 0){
							JOptionPane.showMessageDialog(null,"El valor debe ser distinto de 0");
							txtValor.requestFocus();
						}else {
							Oferta oferta = factoria.getOferta(txtEntidad.getText(), fechaIni, fechaFin, Integer.parseInt(txtValor.getText()), txtCodigo.getText(), cbxTipo.getSelectedIndex(), 0);
							// una vez creado el descuento, lo añadismo a los descuentos asociados al cliente
							ofertas.add(oferta);
							// Añadimos al mensaje del cliente el descuento a añadir y el dueño del descuento.
							session.put("Descuento", oferta);
							session.put("Tipo", cbxTipo.getSelectedIndex());
							session.put("Customer", customer);
							cliente.setSession(session);
							// Y finalmente hacemos la comunicacion con el servidor para el añadido del descuento.
							// Una vez finalizado, se cierra el entorno gráfico y se abre la pantalla de inicio donde están todos los descuentos asociados a ese cliente.
							cliente.run(cliente);
							setVisible(false);
							// Al añadir el descuento, se abre la de inicio
							JInicio ini = new JInicio(customer, cliente);
						}
					}
					catch (PorcentajeException exc){
						JOptionPane.showMessageDialog(null, exc);
						txtValor.requestFocus();
					}
					catch (NumberFormatException exc){
						JOptionPane.showMessageDialog(null,"El valor debe ser un numero entero.");
						txtValor.requestFocus();

					}
				}
            }

		});
		
		
		btnCrear.addKeyListener(new java.awt.event.KeyAdapter() 
        	{
		    // Método añadir descuento:
		    public void keyPressed(java.awt.event.KeyEvent e)
            {
				String fechaIni = cbxDiaIni.getSelectedItem() + "/" + cbxMesIni.getSelectedItem() + "/" + cbxAñoIni.getSelectedItem();
				String fechaFin = cbxDiaFin.getSelectedItem() + "/" + cbxMesFin.getSelectedItem() + "/" + cbxAñoFin.getSelectedItem();
				Date dateIni;
				Date dateFin;
				try{
					dateIni = new SimpleDateFormat("dd/MM/yyyy").parse(fechaIni);
					dateFin = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFin);
				}
				catch(Exception exception){
					dateIni = new Date();
					dateFin = new Date();
				}

				//Varias excepciones como que si están vacíos te salga un aviso o que la fecha final no sea mayor a la inicial
				//o que el número que introduzcas del decuento no sea entero
				if(txtCodigo.getText().equals("") || txtEntidad.getText().equals("") || txtValor.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Introduce todos los campos");
					if(txtCodigo.getText().equals("")){
						txtCodigo.requestFocus();
					}
					else if(txtEntidad.getText().equals("")){
						txtEntidad.requestFocus();
					}
					else{
						txtValor.requestFocus();
					}
				}
				else if(dateIni.compareTo(dateFin) > 0 ){
					JOptionPane.showMessageDialog(null,"Introduce una fecha de inicio y una fecha de fin correctas");
					cbxDiaIni.requestFocus();
				}
				else if(Checker.onlyDigits(txtValor.getText(),txtValor.getText().length())!=true){
					JOptionPane.showMessageDialog(null,"El valor debe ser un numero entero positivo");
					txtValor.requestFocus();
				}else if(Integer.parseInt(txtValor.getText()) == 0){
					JOptionPane.showMessageDialog(null,"El valor debe ser distinto de 0");
					txtValor.requestFocus();
				}
				else{
					// Como debemos hacer un a conexión con el servidor para añadir el descuento
					// comenzamos a crear el mensaje de envio.
					// Establevcemos el contexto : añadir descuento
					try {

						cliente.setContext("/addDescuento");
						HashMap<String, Object> session = new HashMap<String, Object>();
						//Aquí creamos las fechas en función de lo introducido por el usuario, separando la información por barras
						//Formato dd/mm/aaaa
						//fechaIni = cbxDiaIni.getSelectedIndex() + "/" + cbxMesIni.getSelectedIndex() + "/" + cbxAñoIni.getSelectedIndex();
						//fechaFin = cbxDiaFin.getSelectedIndex() + "/" + cbxMesFin.getSelectedIndex() + "/" + cbxAñoFin.getSelectedIndex();
						//Creamos el descuento a añadir en función de los parámetros introducidos por el usuario en la pantalla.
						//Oferta oferta = new Oferta(txtEntidad.getText(), fechaIni,fechaFin,Integer.parseInt(txtValor.getText()),txtCodigo.getText());
						Oferta oferta = factoria.getOferta(txtEntidad.getText(), fechaIni, fechaFin, Integer.parseInt(txtValor.getText()), txtCodigo.getText(), cbxTipo.getSelectedIndex(), 0);
						// una vez creado el descuento, lo añadismo a los descuentos asociados al cliente
						ofertas.add(oferta);
						// Añadimos al mensaje del cliente el descuento a añadir y el dueño del descuento.
						session.put("Descuento", oferta);
						session.put("Tipo", cbxTipo.getSelectedIndex());
						session.put("Customer", customer);
						cliente.setSession(session);
						// Y finalmente hacemos la comunicacion con el servidor para el añadido del descuento.
						// Una vez finalizado, se cierra el entorno gráfico y se abre la pantalla de inicio donde están todos los descuentos asociados a ese cliente.
						cliente.run(cliente);
						setVisible(false);
						// Al añadir el descuento, se abre la de inicio
						JInicio ini = new JInicio(customer, cliente);
					}
					catch (PorcentajeException exc){
						JOptionPane.showMessageDialog(null, exc);
						txtValor.requestFocus();
					}
					catch (NumberFormatException exc){
						JOptionPane.showMessageDialog(null,"El valor debe ser un numero entero.");
						txtValor.requestFocus();

					}
				}
            }

        });
		pnlSur.add(btnCrear);

	//LADOS
		JPanel pnlEste = new JPanel();
		pnlEste.setPreferredSize(new Dimension(50, 60));

		JPanel pnlOeste = new JPanel();
		pnlOeste.setPreferredSize(new Dimension(50, 60));

//FUNCIONES
		txtEntidad.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER) //valor de key: enter
					cbxDiaIni.requestFocus();
					//txtInicio.requestFocus();
				
			}
		});

		/*txtInicio.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER) //valor de key: enter
					txtFin.requestFocus();
			}
		});

		txtFin.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER) //valor de key: enter
					cbxTipo.requestFocus();
				
			}
		});*/

		txtValor.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER) //valor de key: enter
					txtCodigo.requestFocus();
				
			}
		});
		txtCodigo.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER) //valor de key: enter
					btnCrear.requestFocus();
				
			}
		});

		pnlCentro.add(lblEntidad);
		pnlCentro.add(txtEntidad);
		pnlCentro.add(lblInicio);
		pnlCentro.add(pnlFechaIni);
		pnlCentro.add(lblFin);
		pnlCentro.add(pnlFechaFin);
		pnlCentro.add(lblTipo);
		pnlCentro.add(cbxTipo);
		pnlCentro.add(lblValor);
		pnlCentro.add(txtValor);
		pnlCentro.add(lblCodigo);
		pnlCentro.add(txtCodigo);
		

		this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlNorte, BorderLayout.NORTH);
		this.add(pnlSur, BorderLayout.SOUTH);
		this.add(pnlEste, BorderLayout.EAST);
		this.add(pnlOeste, BorderLayout.WEST);

		//Al cerrar la ventana, se abre la de inicio anterior
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JInicio ini = new JInicio(customer, cliente);

			}
		});
		//this.pack();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setResizable(false);
		this.setVisible(true); 
		this.setLocation(550, 250);
	}

	
}
