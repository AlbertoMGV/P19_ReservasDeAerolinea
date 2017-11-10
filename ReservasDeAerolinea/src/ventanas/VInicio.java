package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Airport;
import datos.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VInicio extends JFrame {
	
	private Usuario loggedUser;
	private JTextField textField;
	private JTextField textField_1;
	
	private static final int MAX_PASAJEROS = 20;
	
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
		setResizable(false);
		//cambiar look 
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		this.loggedUser = u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 453);
		getContentPane().setLayout(null);
		
		JLabel lblReservaDeVuelos = new JLabel("RESERVA DE VUELOS");
		lblReservaDeVuelos.setBounds(24, 105, 154, 16);
		getContentPane().add(lblReservaDeVuelos);
		
		JRadioButton rdbtnIdaYVuelta = new JRadioButton("Ida y Vuelta");
		rdbtnIdaYVuelta.setBounds(20, 130, 127, 25);
		getContentPane().add(rdbtnIdaYVuelta);
		
		JRadioButton rdbtnSoloIda = new JRadioButton("Solo Ida");
		rdbtnSoloIda.setBounds(168, 130, 127, 25);
		getContentPane().add(rdbtnSoloIda);
		
		JLabel lblOrigen = new JLabel("ORIGEN");
		lblOrigen.setBounds(24, 175, 56, 16);
		getContentPane().add(lblOrigen);
		
		//Hay que conseguir meter todos los aeropuertos de la base de datos aqui
		JComboBox comboBoxOrigen = new JComboBox();
		comboBoxOrigen.setModel(new DefaultComboBoxModel(Airport.getAll().toArray()));
		comboBoxOrigen.setBounds(92, 172, 132, 22);
		getContentPane().add(comboBoxOrigen);
		
		//Hay que conseguir meter todos los aeropuertos de la base de datos aqui
		JComboBox comboBoxDestino = new JComboBox();
		comboBoxDestino.setModel(new DefaultComboBoxModel(Airport.getAll().toArray()));
		comboBoxDestino.setBounds(92, 207, 132, 22);
		getContentPane().add(comboBoxDestino);
		
		JLabel lblDestino = new JLabel("DESTINO");
		lblDestino.setBounds(24, 210, 56, 16);
		getContentPane().add(lblDestino);
		
		JLabel lblNPasajeros = new JLabel("PASAJEROS");
		lblNPasajeros.setBounds(24, 251, 98, 16);
		getContentPane().add(lblNPasajeros);
		
		//Igual mejor en vez de elegir el numero escribirlo
		JComboBox comboBoxPasajeros = new JComboBox();
		String[] nPasajeros = new String[MAX_PASAJEROS];
		for(int i = 0; i < MAX_PASAJEROS; i++) {
			nPasajeros[i]  = (i+1)+"";
		}
		comboBoxPasajeros.setModel(new DefaultComboBoxModel(nPasajeros));
		comboBoxPasajeros.setBounds(134, 248, 41, 22);
		getContentPane().add(comboBoxPasajeros);
		
		JLabel lblTipoBillete = new JLabel("CLASE");
		lblTipoBillete.setBounds(210, 251, 87, 16);
		getContentPane().add(lblTipoBillete);
		
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Turista", "Business" , "Económica"}));
		comboBox_3.setBounds(324, 248, 87, 22);
		getContentPane().add(comboBox_3);
		
		JLabel lblFechaIda = new JLabel("FECHA IDA");
		lblFechaIda.setBounds(24, 280, 80, 16);
		getContentPane().add(lblFechaIda);
		
		JLabel lblFechaVuelta = new JLabel("FECHA VUELTA");
		lblFechaVuelta.setBounds(24, 316, 98, 16);
		getContentPane().add(lblFechaVuelta);
		
		textField = new JTextField();
		textField.setBounds(134, 283, 126, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(134, 313, 126, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEjemploDdmmaaaa = new JLabel("Ejemplo: DD/MM/AAAA");
		lblEjemploDdmmaaaa.setBounds(272, 286, 154, 16);
		getContentPane().add(lblEjemploDdmmaaaa);
		
		//GUARDAR LO SELECCIONADO Y EXCEPCIONES
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Comprueba que todos los campos obligatorios han sido seleccionados", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnAceptar.setBounds(118, 368, 97, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelat = new JButton("CANCELAR");
		btnCancelat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				VLogin frame = new VLogin();
				frame.setVisible(true);
			
			}
		});
		btnCancelat.setBounds(256, 368, 97, 25);
		getContentPane().add(btnCancelat);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("././res/bg.jpg"));
		lblNewLabel.setBounds(41, 13, 385, 79);
		getContentPane().add(lblNewLabel);
	}
}
