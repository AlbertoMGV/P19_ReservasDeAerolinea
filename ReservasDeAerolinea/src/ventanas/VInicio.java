package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Airport;
import datos.Usuario;
import gestores.GestorRutas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.event.ActionEvent;

public class VInicio extends JFrame {
	
	private Usuario loggedUser;
	private JTextField textField;
	private JTextField textField_1;
	
	private Object[] airports;
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
		setBounds(725, 350, 474, 453);
		setTitle("[DeustoAIR] Inicio");
		getContentPane().setLayout(null);
		
		//ordenar arraylist de aeropuertos 
		
		ArrayList<Airport> a =  Airport.getAll();
		Comparator c = new Comparator<Airport>() {
			   @Override
		        public int compare(Airport a2, Airport a1)
		        {

		            return  a2.getCity().compareTo(a1.getCity());
		        }
		};
		a.sort(c);
		
		airports = a.toArray();
		
		JLabel lblReservaDeVuelos = new JLabel("RESERVA DE VUELOS");
		lblReservaDeVuelos.setBounds(24, 105, 154, 16);
		getContentPane().add(lblReservaDeVuelos);
		
		JRadioButton rdbtnIdaYVuelta = new JRadioButton("Ida y Vuelta");
		rdbtnIdaYVuelta.setBounds(20, 130, 127, 25);
		getContentPane().add(rdbtnIdaYVuelta);
		
		JRadioButton rdbtnSoloIda = new JRadioButton("Solo Ida");
		rdbtnSoloIda.setBounds(168, 130, 127, 25);
		rdbtnSoloIda.setSelected(true);
		getContentPane().add(rdbtnSoloIda);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnSoloIda);
		btnGroup.add(rdbtnIdaYVuelta);
		
		JLabel lblOrigen = new JLabel("ORIGEN");
		lblOrigen.setBounds(24, 175, 56, 16);
		getContentPane().add(lblOrigen);
		
		//Hay que conseguir meter todos los aeropuertos de la base de datos aqui
		JComboBox comboBoxOrigen = new JComboBox();
		comboBoxOrigen.setModel(new DefaultComboBoxModel(airports));
		comboBoxOrigen.setSelectedItem(Airport.get("BIO"));
		comboBoxOrigen.setBounds(92, 172, 132, 22);
		getContentPane().add(comboBoxOrigen);
		
		//Hay que conseguir meter todos los aeropuertos de la base de datos aqui
		JComboBox comboBoxDestino = new JComboBox();
		comboBoxDestino.setModel(new DefaultComboBoxModel(airports));
		comboBoxDestino.setSelectedItem(Airport.get("LHR"));
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
		
		
		JComboBox classComboBox = new JComboBox();
		classComboBox.setModel(new DefaultComboBoxModel(new String[] {"Económica", "Turista", "Business" }));
		classComboBox.setBounds(324, 248, 87, 22);
		getContentPane().add(classComboBox);
		
		JLabel lblFechaIda = new JLabel("FECHA IDA");
		lblFechaIda.setBounds(24, 280, 80, 16);
		getContentPane().add(lblFechaIda);
		JComboBox depthComboBox = new JComboBox();
		depthComboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4"}));
		depthComboBox.setSelectedIndex(1);
		depthComboBox.setBounds(352, 173, 41, 20);
		getContentPane().add(depthComboBox);
		
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
				String origen, destino;
				int escalas;
				
				origen = ((Airport) comboBoxOrigen.getSelectedItem()).getIATA();
				destino = ((Airport) comboBoxDestino.getSelectedItem()).getIATA();
				escalas = Integer.parseInt(depthComboBox.getSelectedItem().toString());
				
				ArrayList<String[]> resultados = GestorRutas.getRuta(origen, destino, escalas);
				
				if(rdbtnIdaYVuelta.isSelected() && !resultados.isEmpty()) {
					resultados.addAll(GestorRutas.getRuta(destino, origen, escalas));
				}
				
				for(String[] r : resultados) {
					System.out.println(Arrays.toString(r));
				}
				
				VResultados r = new VResultados(resultados);
				r.setVisible(true);
				
				Airport.resetPrevious();
				
				System.out.println("-------------------");

				//JOptionPane.showMessageDialog(null, "Comprueba que todos los campos obligatorios han sido seleccionados", "Error", JOptionPane.ERROR_MESSAGE);
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
		
		JButton btnMiPerfil = new JButton("Mi perfil");
		btnMiPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				VPerfil frame = new VPerfil(u);
				frame.setVisible(true);
			}
		});
		btnMiPerfil.setBounds(329, 105, 97, 25);
		getContentPane().add(btnMiPerfil);
		
		JLabel lblEscalasMx = new JLabel("Escalas M\u00E1x.");
		lblEscalasMx.setBounds(272, 176, 70, 14);
		getContentPane().add(lblEscalasMx);
		

	}
}
