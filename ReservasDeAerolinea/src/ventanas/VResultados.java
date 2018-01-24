package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import datos.Airport;
import datos.Reserva;
import datos.Usuario;
import datos.Vuelo;
import gestores.GestorDB;
import gestores.Pricing;

import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JButton;
import gestores.GestorDB;
public class VResultados extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<String[]> resultados;
	private ArrayList<Pricing> precios;
	private Usuario loggedUser;
	private DefaultTableModel tbm;
	/**
	 * Create the frame.
	 */
	public VResultados(ArrayList<String[]> resultados, int pasajeros, int claseId, String[] fechas) {
		this.resultados = resultados;
		ArrayList<Pricing> precios = new ArrayList<Pricing>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 500);
		setTitle("[DeustoAIR] Informacion Vuelos");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		loggedUser = VInicio.getLoggedUser();
		String[] columnNames = {"Nº Vuelo", "Origen", "Escalas", "Destino", "Precio", "H.Salida", "H.Llegada"};
		tbm = new DefaultTableModel(columnNames, 0) {
			//desactivar modificación de celdas
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		for(String[] s : resultados) {

			Pricing vuelo = Pricing.procesarPrecio(s, fechas, pasajeros, claseId);
			precios.add(vuelo);

			String[] data = {Arrays.toString(vuelo.getAerolineas()), Airport.get(s[0]).getCity(), escalas(s), Airport.get(s[s.length-1]).getCity(), vuelo.getPrecio() + " €", vuelo.gethSalida() + "h", vuelo.gethLlegada()+"h"};


			if(vuelo.getAerolineas().length + 1 == s.length) {
				tbm.addRow(data);
			}


		}
		this.precios = precios;
		JPanel flightsPanel = new JPanel();
		contentPane.add(flightsPanel, BorderLayout.CENTER);
		table = new JTable(tbm);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		flightsPanel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane(table);
		flightsPanel.add(scrollPane);

		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonPanel.add(btnNewButton);

		JButton btnMoreInfo = new JButton("M\u00E1s informaci\u00F3n...");
		buttonPanel.add(btnMoreInfo);
		btnMoreInfo.addActionListener(masInfoListener);

		JButton btnReservar = new JButton("Reservar");
		buttonPanel.add(btnReservar);
		btnReservar.addActionListener(reservaListener);
		//System.out.println("Current user: " + loggedUser.getName());
	}

	private ActionListener masInfoListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIndex = table.getSelectedRow();
			String[] ruta = resultados.get(selectedIndex);
			Vuelo[] vuelos = new Vuelo[ruta.length - 1];
			double precio = precios.get(selectedIndex).getPrecio();

			for(int i = 0; i < ruta.length - 1; i++) {
				String nVuelo = precios.get(selectedIndex).getAerolineas()[i].replaceAll(" ", "");
				Airport origen = Airport.get(ruta[i]);
				Airport destino = Airport.get(ruta[i+1]);
				String codAerolinea = nVuelo.substring(0, 2);

				Vuelo vuelo = new Vuelo(nVuelo, origen, destino, GestorDB.getAircraft(origen.getIATA(), destino.getIATA(), codAerolinea), 0);
				vuelos[i] = vuelo;
			}

			VInfo infoVuelo = new VInfo(vuelos, precio, loggedUser);
			infoVuelo.setVisible(true);

		}
	};

	private ActionListener reservaListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int selectedIndex = table.getSelectedRow();
			String[] ruta = resultados.get(selectedIndex);
			double precio = precios.get(selectedIndex).getPrecio();
			String hSalida = precios.get(selectedIndex).gethSalida();
			
			int COD_R = 0;
			String sCOD_R = "";
			//generar 9 numeros aleatorios (cod_r)
			
			for(int i = 0; sCOD_R.length() < 9; i++) {
				int random = ThreadLocalRandom.current().nextInt(0, 9);
				sCOD_R += random;
			}
			
			COD_R = Integer.parseInt(sCOD_R);

			for(int i = 0; i < ruta.length - 1; i++) {
				//registrar vuelos iterativamente
				String nVuelo = precios.get(selectedIndex).getAerolineas()[i].replaceAll(" ", "");
				Airport origen = Airport.get(ruta[i]);
				Airport destino = Airport.get(ruta[i+1]);
				String codAerolinea = nVuelo.substring(0, 2);
				
				Vuelo vuelo = new Vuelo(nVuelo, origen, destino, GestorDB.getAircraft(origen.getIATA(), destino.getIATA(), codAerolinea), COD_R);
				GestorDB.regVuelo(vuelo);				
			}
			
			
			Reserva reserva = new Reserva(COD_R, precio, loggedUser.getDni());
				GestorDB.regReserva(reserva);
				
				dispose();
				JOptionPane.showMessageDialog(null, "Reserva realizada", "[DeustoAir] Información", JOptionPane.NO_OPTION);
			
			
		}
	};

	private String escalas(String[] input) {
		String result="";
		for(int i = 1; i < input.length-1; i++) {
			result+= Airport.get(input[i]).getCity()+ ", ";
		}
		if(!result.equals("")) {
			result = result.substring(0, result.length()-2);
		}
		return result;
	}


}
