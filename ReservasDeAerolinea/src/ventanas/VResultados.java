package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import datos.Airport;
import datos.Usuario;
import gestores.Pricing;

import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class VResultados extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Usuario loggedUser;
	/**
	 * Create the frame.
	 */
	public VResultados(ArrayList<String[]> resultados, int pasajeros, int claseId, String[] fechas) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		loggedUser = VInicio.getLoggedUser();
		String[] columnNames = {"Nº Vuelo", "Origen", "Escalas", "Destino", "Precio", "H.Salida", "H.Llegada"};
		DefaultTableModel tbm = new DefaultTableModel(columnNames, 0) {
			//desactivar modificación de celdas
			@Override
			public boolean isCellEditable(int row, int column) {
			      return false;
			   }
		};
		for(String[] s : resultados) {
			
			Pricing vuelo = Pricing.procesarPrecio(s, fechas, pasajeros, claseId);
			
			String[] data = {Arrays.toString(vuelo.getAerolineas()), Airport.get(s[0]).getCity(), escalas(s), Airport.get(s[s.length-1]).getCity(), vuelo.getPrecio() + " €", "salida", "llegada"};

			
			if(vuelo.getAerolineas().length + 1 == s.length) {
				tbm.addRow(data);
			}
			
			
		}
		
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
		
		JButton btnMoreInfo = new JButton("M\u00E1s informaci\u00F3n...");
		buttonPanel.add(btnMoreInfo);
		
		JButton btnReservar = new JButton("Reservar");
		buttonPanel.add(btnReservar);
		System.out.println("Current user: " + loggedUser.getName());
	}
	
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
