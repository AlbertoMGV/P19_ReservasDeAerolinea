package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Airport;
import datos.Reserva;
import datos.Usuario;
import datos.Vuelo;
import gestores.GestorDB;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.FlatteningPathIterator;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class VInfo extends JFrame {

	private JPanel contentPane;
	private JPanel flightsPanel;
	private Vuelo[] vuelos;
	private double precio;
	private Usuario loggedUser;
	private String hSalida;
	private String[] fechas1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vuelo ejemplo = new Vuelo("IB-XXXX", Airport.get("BIO"), Airport.get("MAD"), GestorDB.getAircraft("BIO", "MAD", "UX"),0);
					Vuelo ejemplo2 = new Vuelo("AZ-XXXX", Airport.get("MAD"), Airport.get("FCO"), GestorDB.getAircraft("MAD", "FCO", "AZ"),0);

					Vuelo[] vuelos = {ejemplo, ejemplo2};
					VInfo frame = new VInfo(vuelos, 0, "",null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VInfo(Vuelo[] vuelos, double precio, String hSalida ,Usuario loggedUser, String[] fechas) {
		this.vuelos = vuelos;
		this.precio = precio;
		this.loggedUser = loggedUser;
		this.hSalida = hSalida;
		this.fechas1=fechas;
		
		//cambiar look 

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		setTitle("[DeustoAIR] Informacion vuelo seleccionado");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel titlePanel = new JPanel();
		contentPane.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("Informaci\u00F3n sobre el vuelo elegido:");
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		titlePanel.add(lblTitle);
		
		flightsPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(flightsPanel);
		//contentPane.add(flightsPanel, BorderLayout.CENTER);
		flightsPanel.setLayout(new BoxLayout(flightsPanel, BoxLayout.Y_AXIS));
		int vuelo_n = 1;
		for(Vuelo v : vuelos) {
			JPanel panel = v.getJPanel();
			JLabel vueloId = new JLabel("Vuelo nº " + vuelo_n);
			vueloId.setFont(new Font("Tahoma", Font.BOLD, 15));
			flightsPanel.add(vueloId);
			flightsPanel.add(panel, BorderLayout.CENTER);
			flightsPanel.add(Box.createVerticalStrut(5));
			vuelo_n++;
		}
		
		contentPane.add(scroll);
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		
		JButton btnCerrar = new JButton("Cerrar");
		bottomPanel.add(btnCerrar);
		btnCerrar.addActionListener(cerrarListener);
		
		JButton btnReservar = new JButton("Reservar");
		bottomPanel.add(btnReservar);
		btnReservar.addActionListener(reservaListener);
	}


	private ActionListener cerrarListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();			
		}
	};

	private ActionListener reservaListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
						
			int COD_R = 0;
			String sCOD_R = "";
			//generar 9 numeros aleatorios (cod_r)
			
			while(sCOD_R.length() < 9){
				int random = ThreadLocalRandom.current().nextInt(0, 9);
				sCOD_R += random;
			}
			
			COD_R = Integer.parseInt(sCOD_R);

			for(int i = 0; i < vuelos.length; i++) {
				//registrar vuelos iterativamente
				vuelos[i].setCodReserva(COD_R);
				GestorDB.regVuelo(vuelos[i]);
				
			}
			
			
			Reserva reserva = new Reserva(COD_R, precio, loggedUser.getDni(), hSalida,fecha);
			GestorDB.regReserva(reserva);
			dispose();
			JOptionPane.showMessageDialog(null, "Reserva realizada", "[DeustoAir] Información", JOptionPane.NO_OPTION);
			
			
		}
	};

}
