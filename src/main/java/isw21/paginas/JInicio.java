package main.java.isw21.paginas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JScrollPane;

import main.java.isw21.descuentos.ChequeRegalo;
import main.java.isw21.descuentos.Descuento;
import main.java.isw21.descuentos.Oferta;
import main.java.isw21.descuentos.Porcentaje;
import main.java.isw21.domain.Customer;
import main.java.isw21.client.Client;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;



/**
 * La ventana principal al abrir la aplicación en la que se muestran las ofertas del usuario.
 * @version 0.3
 */
public class JInicio extends JFrame
{
	Client cliente;
	ArrayList<Oferta> plOfertas;
	private Timer timer;
	public JFrame parent= new JFrame();

	Customer customer;
	// Cada pantalla de inicio irá asociada a un solo costumer. Además como queremos mostrar los descuentos asociados a este nada más entrar,
	// tendremos que tener disponible la conexion con el servidor mediante el cliente.
	public JInicio(Customer customer,Client cliente)
	{
		//Establecemos la conexión y el dueño de los descuentos
		this.customer = customer;
		this.cliente = cliente;

		// DESCRIPCION DEL ENTORNO GRAFICO


		setSize(600,600);
		this.setLayout(new BorderLayout());
		Font fuente1 = new Font("Serif", 1, 18);

		//NORTE
		JPanel pnlNorte = new JPanel();
		pnlNorte.setBackground(new Color(255,255,255));
		pnlNorte.setLayout(new GridLayout(1,3));
		JLabel l5 = new JLabel();
		ImageIcon imagen1 = new ImageIcon("src/main/java/isw21/media/LogoDiskAnt.jpeg");
		l5.setIcon(new ImageIcon(imagen1.getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH)));
		JLabel l6 = new JLabel("MIS DESCUENTOS");
		l6.setFont(fuente1);
		JLabel l7 = new JLabel();
		pnlNorte.setPreferredSize(new Dimension(600, 50));

		pnlNorte.add(l5);
		pnlNorte.add(l6);
		pnlNorte.add(l7);

		// getDescuentos devuelve todos los descuentos del que el cliente es el dueño
		plOfertas = getDescuentos(customer);
		//eliminamos el primero ya que siempre nos devuelve en la primera posicion un descuento generico.
		plOfertas.remove(0);


		//CENTRO
		//Establecemos el entorno gráfico dependiendo de los descuentos que tenga asociados un cliente
		JPanel pnlCentro = new JPanel();
		pnlCentro.setBackground(new Color(174,200,178));
		int l= plOfertas.size();
		pnlCentro.setLayout(new FlowLayout());
		// Al iniciar la pestaña, se mostrarán los descuentos asociados a la cuenta

		//Si no hay, se mostrará un mensaje: "En este momento no tienes descuentos".
		if(plOfertas == null || l == 0){
			JLabel lno = new JLabel("En este momento no tienes descuentos");
			lno.setFont(fuente1);
			pnlCentro.add(lno);
			this.add(pnlCentro, BorderLayout.CENTER);
			//pnlCentro.add(btnCrearDescuento);
			//Quitar el descuento inicial de bienvenida
		}
		//En caso contrario visualizarán en el centro de la pestaña
		else {
			pnlCentro.setPreferredSize(new Dimension(0, l*105));
			for (Oferta i : plOfertas) {
				//Para cada descuento que tenga el usuario, se llamará a la funcion mastrar. La cual organiza los descuentos y los muestra al usuario


				mostrarDescuento(i,pnlCentro,fuente1);



			}
			JScrollPane scroll = new JScrollPane(pnlCentro);
			scroll.setViewportView(pnlCentro);

			setVisible(true);
			this.add(scroll, BorderLayout.CENTER);
		}



		//SUR

		JPanel pnlSur = new JPanel();
		pnlSur.setLayout(new GridLayout(1, 5));

		JButton btnMas = new JButton();
		btnMas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ImageIcon imagenMas = new ImageIcon("src/main/java/isw21/media/Mas.png");
		btnMas.setIcon(new ImageIcon(imagenMas.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

		JButton btnLupa = new JButton();
		btnLupa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ImageIcon imagenLupa = new ImageIcon("src/main/java/isw21/media/Lupa.png");
		btnLupa.setIcon(new ImageIcon(imagenLupa.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

		JButton btnHome = new JButton();
		btnHome.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ImageIcon imagenHome = new ImageIcon("src/main/java/isw21/media/Home.png");
		btnHome.setIcon(new ImageIcon(imagenHome.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

		JButton btnCorazon = new JButton();
		btnCorazon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ImageIcon imagenCorazon = new ImageIcon("src/main/java/isw21/media/Corazon.png");
		btnCorazon.setIcon(new ImageIcon(imagenCorazon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

		JButton btnMiPerfil= new JButton();
		btnMiPerfil.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ImageIcon imagen = new ImageIcon("src/main/java/isw21/media/Perfil.png");
		btnMiPerfil.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));

		pnlSur.add(btnMas);
		pnlSur.add(btnLupa);
		pnlSur.add(btnHome);
		pnlSur.add(btnCorazon);
		pnlSur.add(btnMiPerfil);



		btnHome.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JInicio inicio = new JInicio(customer, cliente);
				setVisible(false);
			}
		});

		//Al pulsar la lupa, te llevará a una ventana en la que podrás buscar por comercio entre tus descuentos
		btnLupa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String n= (String) JOptionPane.showInputDialog("Introduzca el comercio del que quiera buscar la oferta:");
				if(n!=null){
					pnlCentro.removeAll();
					pnlCentro.setVisible(false);
					System.out.println(n);
					plOfertas = getDescuentos(customer);
					plOfertas.remove(0);
					ArrayList<Oferta> plOfertasFiltradas= new ArrayList<Oferta>();
					for (Oferta of: plOfertas){
						if (of.getComercio().equals(n) || n.equals("")){
							plOfertasFiltradas.add(of);
						}
					}
					int l= plOfertasFiltradas.size();
					//Esto habrá que cambiarlo, pero de momento con 16 está nice
					pnlCentro.setLayout(new GridLayout(4, 4));

					// Al iniciar la pestaña, se mostrarán los descuentos asociados a la cuenta

					//Si no hay, se mostrará un mensaje: "En este momento no tienes descuentos".
					if(plOfertasFiltradas == null || l == 0	){
						JLabel lno = new JLabel("En este momento no tienes descuentos de ese comercio");
						lno.setFont(fuente1);
						pnlCentro.add(lno);
						//pnlCentro.add(btnCrearDescuento);
						//Quitar el descuento inicial de bienvenida
					}
					//En caso contrario visualizarán en el centro de la pestaña
					else {

						for (Oferta of : plOfertasFiltradas) {
							//Para cada descuento que tenga el usuario, se llamará a la funcion mastrar. La cual organiza los descuentos y los muestra al usuario
							mostrarDescuento(of,pnlCentro,fuente1);


						}
					}
					pnlCentro.setVisible(true);
				}
			}
		});

//FUNCIONES
		/*btnCorazon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IODescuento.escribirOferta(plOfertas,"comillas");

			}
		});*/

		// El botón del más te lleva a agregar descuentos
		btnMas.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Si seleccionamos la opcion de crer descuento, abrimos el entorno gráfico necesario y
				// le introducimos como parámetros, el dueño, la conexion con el servidor y los descuentos asociados al dueño.
				//JDescuento descuento = new JDescuento(customer,cliente, plOfertas);
				JAgregar agregar = new JAgregar(customer,cliente, plOfertas);
				setVisible(false);
				//mostrarDescuento(plDescuentos.get(plDescuentos.size()-1),pnlCentro);

			}
		});

		//El botón de la cara te lleva al perfil
		btnMiPerfil.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JMiPerfil perfil = new JMiPerfil(customer,cliente, plOfertas);
				setVisible(false);
			}
		});


        /*timer = new Timer(1000,e ->{
            mostrarDescuentos();
        });
        timer.start();*/

		// El botón del corazón te lleva al informe de ahorro
		btnCorazon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JAhorro ahorro = new JAhorro(customer, cliente);
				setVisible(false);
			}
		});


		this.add(pnlNorte, BorderLayout.NORTH);

		this.add(pnlSur, BorderLayout.SOUTH);


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocation(250, 100);
	}


	/**
	 * Método de visualización de descuentos. Se ejecutará para los descuentos que tenga el usuario
	 * @param i oferta a mostrar
	 * @param pnlCentro el panel donde se muestran
	 * @param fuente1 la letra y color al mostrarlo
	 */
	private void mostrarDescuento(Oferta i , JPanel pnlCentro, Font fuente1) {
		String tipo = comprobarTipo(i);
		JPanel pnlDescuento = new JPanel();
		pnlDescuento.setPreferredSize(new Dimension(500, 100));
		pnlDescuento.setLayout(new GridLayout(6, 1));
		if (i instanceof ChequeRegalo) {
			pnlDescuento.setLayout(new GridLayout(7, 1));
			ChequeRegalo cheque = (ChequeRegalo) i;
			JLabel lblGastado = new JLabel("Gastado: " + cheque.getGastado(), JLabel.CENTER);
		}
		JLabel lblComercio = new JLabel(i.getComercio(), JLabel.CENTER);
		JLabel lblValor = new JLabel("Valor: " + i.getValor(), JLabel.CENTER);
		JLabel lblTipo = new JLabel("Tipo: " + tipo, JLabel.CENTER);
		JLabel lblCodigo = new JLabel("Codigo: " + i.getCodigo(), JLabel.CENTER);
		JLabel lblFechaFin = new JLabel("Caduca: " + i.getFechaFin(),JLabel.CENTER);
		JButton btnInfo = new JButton("Info del descuento");
		JButton btnCopiar = new JButton("Copiar");
		JButton btnUsar = new JButton("Usar descuento");
		JButton btnEliminar = new JButton("Eliminar descuento");

		lblComercio.setBorder(BorderFactory.createEtchedBorder());
		//new JLabel( "prueba",JLabel.CENTER
		JPanel pnlBotones = new JPanel(new GridLayout(1,3));


		pnlDescuento.add(lblComercio);
		pnlDescuento.add(lblValor);
		if (i instanceof ChequeRegalo) {
			pnlDescuento.setLayout(new GridLayout(7, 1));
			ChequeRegalo cheque = (ChequeRegalo) i;
			JLabel lblGastado = new JLabel("Gastado: " + cheque.getGastado(), JLabel.CENTER);
			pnlDescuento.add(lblGastado);
		}
		pnlDescuento.add(lblTipo);
		pnlDescuento.add(lblCodigo);
		pnlDescuento.add(lblFechaFin);
		pnlBotones.add(btnInfo);
		pnlBotones.add(btnCopiar);
		pnlBotones.add(btnUsar);
		pnlBotones.add(btnEliminar);
		pnlDescuento.add(pnlBotones);

		//Botón que te permite copiar al portapapeles el descuento que quieras
		btnCopiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(i.getCodigo());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				JOptionPane.showMessageDialog(null,"Se ha copiado el código del descuento al portapapeles.");
			}
		});

		// Botón que te permite usar tu descuento, se añadirá a tu informe de ahorro
		btnUsar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String,Object> session= new HashMap<String,Object>();
				if (i instanceof ChequeRegalo){
					ChequeRegalo cheque = (ChequeRegalo) i;
					String seleccion = JOptionPane.showInputDialog(null,"Qué cantidad del cheque has gastado? (número)");
					try{
						double valorCheque = Double.parseDouble(seleccion);
						if (valorCheque + cheque.getGastado() >= cheque.getValor()){
							JOptionPane.showMessageDialog(null,"El cheque se ha agotado. Se eliminará de tus descuentos");
							customer.setAhorrado("Cheque",cheque.getValor()-cheque.getGastado());
							cheque.setGastado(cheque.getValor());
							eliminarDescuento(i,pnlCentro,fuente1);
						}else {
							customer.setAhorrado("Cheque", valorCheque);
							cheque.setGastado(valorCheque + cheque.getGastado());
						}
						double[] ahorrado = customer.getAhorrado();
						//lo actualizamos en la base de datos
						cliente.setContext("/updateCheque");
						session.put("Customer",customer);
						session.put("Cheque",cheque);
						session.put("Ahorrado",ahorrado[2]);
						cliente.setSession(session);
						cliente.run(cliente);
						//OfertaDAO.updateGastado(customer,cheque);
						setVisible(false);
						new JInicio(customer,cliente);

					}
					catch (NumberFormatException num){
						JOptionPane.showMessageDialog(parent,"El valor debe ser un número.");
					}
				}else{
					double valor = i.getValor();
					int confirmar = JOptionPane.showConfirmDialog(null,"¿Estas seguro de que quieres utilizar esta oferta? Se borrará y aparecerá en tu ahorro");
					if (JOptionPane.OK_OPTION == confirmar){
						//setVisible(false);
						if (i instanceof Descuento) {
							customer.setAhorrado("Descuento", valor);
							double[] ahorrado = customer.getAhorrado();
							//lo actualizamos en la base de datos
							cliente.setContext("/updateDescuento");
							session.put("Customer",customer);
							session.put("Ahorrado",ahorrado[0]);
							cliente.setSession(session);
							cliente.run(cliente);
						}else if (i instanceof Porcentaje){
							customer.setAhorrado("Porcentaje", valor);
							double[] ahorrado = customer.getAhorrado();
							int numpercs = customer.getNumPorcentajes();
							//lo actualizamos en la base de datos
							cliente.setContext("/updatePorcentaje");
							session.put("Customer",customer);
							session.put("Ahorrado",ahorrado[1]);
							session.put("Numero", numpercs);
							cliente.setSession(session);
							cliente.run(cliente);
						}
						eliminarDescuento(i,pnlCentro,fuente1);
						//new JInicio(customer, cliente);
					}
				}
			}
		});

		// Se muestra la información del descuento
		btnInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Entidad del descuento: "+
						i.getComercio()+"\n Fecha de finalizacion del descuento: " + i.getFechaFin()+
						"\n Valor del descuento: "+i.getValor() + "\n Código del descuento: "+ i.getCodigo());
			}
		});

		//Se elimina la oferta del usuario
		btnEliminar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)

			{
				int dialogResult = JOptionPane.showConfirmDialog(null,"¿Desea borrar el descuento de "+i.getComercio()+"?");

				if (dialogResult == JOptionPane.YES_OPTION){
					eliminarDescuento(i,pnlCentro,fuente1);

				}
				// Si seleccionamos la opcion de crer descuento, abrimos el entorno gráfico necesario y
				// le introducimos como parámetros, el dueño, la conexion con el servidor y los descuentos asociados al dueño.

				//mostrarDescuento(plDescuentos.get(plDescuentos.size()-1),pnlCentro);

			}
		});

		pnlDescuento.setAlignmentX(lblComercio.CENTER_ALIGNMENT);
		pnlDescuento.setAlignmentX(lblValor.CENTER_ALIGNMENT);
		pnlDescuento.setAlignmentX(lblTipo.CENTER_ALIGNMENT);
		pnlDescuento.setAlignmentX(lblCodigo.CENTER_ALIGNMENT);
		pnlDescuento.setAlignmentX(lblFechaFin.CENTER_ALIGNMENT);

		pnlDescuento.setBorder(BorderFactory.createEtchedBorder());

		pnlCentro.add(pnlDescuento);
	}

	private String comprobarTipo(Oferta i) {
		if (i instanceof Descuento){
			return "Descuento";
		}else if (i instanceof Porcentaje){
			return "Porcentaje";
		}else if (i instanceof ChequeRegalo){
			return "ChequeRegalo";
		}else{
			return "Oferta";
		}
	}

	// Metodo que obtiene los descuentos de cada cliente.

	/**
	 * Obtiene los descuentos del usuario, para poder mostrarlos luego
	 * @param customer
	 * @return
	 */
	public ArrayList<Oferta> getDescuentos(Customer customer)
	{
		//Hacemos la solicitud a la base de datos medainte un mensaje con contexto de getDescuentos.
		cliente.setContext("/getDescuentos");
		HashMap<String,Object> session= new HashMap<String,Object>();
		// Los campos que tendrá el mensaje de salida serán el customer --> dueño del descuento
		// Y un arraylist donde queremos que se guarden los descuentos. Para el correcto funcionamiento, pondremos
		// dentro del arraylist un descuento generico para no tener problemas con la lista vacia
		session.put("Customer",customer);
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas.add(new Descuento("Diskant (Bienvenida)","Hoy","Nunca",50,"DISKANTMOLA"));
		session.put("Descuentos", ofertas);
		cliente.setSession(session);
		//Mandamos la comunicacion al servidor
		cliente.run(cliente);
		// Una vez hecha la conexion, el cliente ya tiene los descuentos del usuario
		return cliente.getDescuentos();
	}

	/**
	 * Permite eliminar descuentos del usuario a través de la fachada, del controler y los DAO
	 * @param i
	 * @param pnlCentro
	 * @param fuente1
	 */
	private void eliminarDescuento(Oferta i, JPanel pnlCentro, Font fuente1){
		String eliminado= i.getComercio();

		HashMap<String,Object> session= new HashMap<String,Object>();
		cliente.setContext("/eliminarOferta");
		session.put("Customer",customer);
		session.put("Oferta",i);
		cliente.setSession(session);
		cliente.run(cliente);

		//OfertaDAO.eliminarDescuento(customer,i);
		JOptionPane.showMessageDialog(parent,"Se ha eliminado el descuento de "+eliminado+" y todos los descuentos duplicados.");
		setVisible(false);
		new JInicio(customer,cliente);

	}


}
