package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VInicio extends JFrame {
	
	private Usuario loggedUser;
	private JTextField textField;
	private JTextField textField_1;
	
	public static void crearVInicio(Usuario u) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VInicio frame = new VInicio(u);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VInicio(Usuario u) {
		//cambiar look 
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		this.loggedUser = u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 490);
		getContentPane().setLayout(null);
		
		JLabel lblReservaDeVuelos = new JLabel("RESERVA DE VUELOS");
		lblReservaDeVuelos.setBounds(12, 13, 154, 16);
		getContentPane().add(lblReservaDeVuelos);
		
		JRadioButton rdbtnIdaYVuelta = new JRadioButton("Ida y Vuelta");
		rdbtnIdaYVuelta.setBounds(8, 38, 127, 25);
		getContentPane().add(rdbtnIdaYVuelta);
		
		JRadioButton rdbtnSoloIda = new JRadioButton("Solo Ida");
		rdbtnSoloIda.setBounds(156, 38, 127, 25);
		getContentPane().add(rdbtnSoloIda);
		
		JLabel lblOrigen = new JLabel("ORIGEN");
		lblOrigen.setBounds(12, 83, 56, 16);
		getContentPane().add(lblOrigen);
		
		//Hay que conseguir meter todos los aeropuertos de la base de datos aqui
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"BILBAO", "MADRID", "BARCELONA", "PARIS", "BERLIN", "ROMA", "LISBOA"}));
		comboBox.setBounds(80, 80, 132, 22);
		getContentPane().add(comboBox);
		
		//Hay que conseguir meter todos los aeropuertos de la base de datos aqui
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"BILBAO", "MADRID", "BARCELONA", "PARIS", "BERLIN", "ROMA", "LISBOA"}));
		comboBox_1.setBounds(80, 115, 132, 22);
		getContentPane().add(comboBox_1);
		
		JLabel lblDestino = new JLabel("DESTINO");
		lblDestino.setBounds(12, 118, 56, 16);
		getContentPane().add(lblDestino);
		
		JLabel lblNPasajeros = new JLabel("N\u00BA PASAJEROS");
		lblNPasajeros.setBounds(12, 159, 98, 16);
		getContentPane().add(lblNPasajeros);
		
		//Igual mejor en vez de elegir el numero escribirlo
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
		comboBox_2.setBounds(122, 156, 41, 22);
		getContentPane().add(comboBox_2);
		
		JLabel lblTipoBillete = new JLabel("CLASE");
		lblTipoBillete.setBounds(198, 159, 87, 16);
		getContentPane().add(lblTipoBillete);
		
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Turista", "Business" , "Económica"}));
		comboBox_3.setBounds(312, 156, 87, 22);
		getContentPane().add(comboBox_3);
		
		JLabel lblFechaIda = new JLabel("FECHA IDA");
		lblFechaIda.setBounds(12, 188, 80, 16);
		getContentPane().add(lblFechaIda);
		
		JLabel lblFechaVuelta = new JLabel("FECHA VUELTA");
		lblFechaVuelta.setBounds(12, 224, 98, 16);
		getContentPane().add(lblFechaVuelta);
		
		textField = new JTextField();
		textField.setBounds(122, 191, 126, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(122, 221, 126, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEjemploDdmmaaaa = new JLabel("Ejemplo: DD/MM/AAAA");
		lblEjemploDdmmaaaa.setBounds(260, 194, 154, 16);
		getContentPane().add(lblEjemploDdmmaaaa);
		
		//GUARDAR LO SELECCIONADO Y EXCEPCIONES
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Comprueba que todos los campos obligatorios han sido seleccionados", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnAceptar.setBounds(122, 405, 97, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelat = new JButton("CANCELAR");
		btnCancelat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				VLogin frame = new VLogin();
				frame.setVisible(true);
			
			}
		});
		btnCancelat.setBounds(260, 405, 97, 25);
		getContentPane().add(btnCancelat);
	}
}
