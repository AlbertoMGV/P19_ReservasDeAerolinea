package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import datos.Airport;

import javax.swing.JTable;

public class VResultados extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VResultados frame = new VResultados(null);
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
	public VResultados(ArrayList<String[]> resultados) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String[] columnNames = {"Nº Vuelo", "Origen", "Escalas", "Destino", "Precio", "H.Salida", "H.Llegada"};
		DefaultTableModel tbm = new DefaultTableModel(columnNames, 0);
		table = new JTable(tbm);
		
		for(String[] s : resultados) {


			String[] data = {"n_vuelo", Airport.get(s[0]).getCity(), escalas(s), Airport.get(s[s.length-1]).getCity(), "precio", "salida", "llegada"};
			
			tbm.addRow(data);
		}
		
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		contentPane.setLayout(new BorderLayout(0, 0));
		table.setBounds(10, 11, 649, 395);
		contentPane.add(new JScrollPane(table));
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
