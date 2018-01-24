package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;

import datos.Reserva;
import datos.Usuario;
import datos.Vuelo;
import gestores.GestorDB;

public class VPerfil extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tbmperf;
	private JTree arbol;
	private int selectedReserva;
	private Usuario u;
	private JFrame frame = this;

	/**
	 * Launch the application.
	 */
	public static void crearVPerfil(Usuario u) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VPerfil frame = new VPerfil(u);
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
	public VPerfil(Usuario u) {
		selectedReserva = 0;
		this.u = u;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(25, 350, 700, 442);
		setTitle("[DeustoAIR] Mi Perfil");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//cambiar iconos del jtree
		
		Icon planeIcon =  new ImageIcon("res/imagenes/plane_icon_s.png");
		Icon ticketIcon =  new ImageIcon("res/imagenes/ticket_icon_s.png");
		
		UIManager.put("Tree.closedIcon", ticketIcon);
		UIManager.put("Tree.openIcon", ticketIcon);
		UIManager.put("Tree.leafIcon", planeIcon);


		String[] columnNames = {"NºReserva", "Precio", "Dia", "Hora de Salida"};
		tbmperf = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;	
			}
		};

		//lista de reservas 
		ArrayList<Reserva> lstrsv = GestorDB.listReservas(u);
		
		
		//lista de vuelos
		ArrayList<String> lstvls;
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("RESERVAS");
				
		for (int i = 0; i < lstrsv.size(); i++) {
			
			//cada registro es un root
			DefaultMutableTreeNode reservas = new DefaultMutableTreeNode(lstrsv.get(i).toString());
			//los vuelos seran los hijos
			lstvls = GestorDB.getVuelosRes(lstrsv.get(i));
			
			for (int j = 0; j < lstvls.size(); j++) {
				DefaultMutableTreeNode vuelos = new DefaultMutableTreeNode(lstvls.get(j));
				reservas.add(vuelos);
			}
			
			
			root.add(reservas);
			
			
		}	
		
		arbol = new JTree(root);
		
		arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		arbol.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
				if(nodo == null) return;
								
				Object nodeInfo = nodo.getUserObject();
				
				if(!nodo.isLeaf()) {
					String reserva = (String) nodo.getUserObject();
					String codString = reserva.split(" - ")[0];
					codString = codString.substring(14, codString.length());
					selectedReserva = Integer.parseInt(codString);
				}else {
					selectedReserva = -1;
				}
				
			}
		});

		JScrollPane panelscroll = new JScrollPane(arbol);
		panelscroll.setBounds(12, 206, 670, 146);
		contentPane.add(panelscroll);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("././res/user.jpg"));
		lblNewLabel.setBounds(12, 13, 120, 170);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(165, 42, 56, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(165, 84, 31, 22);
		contentPane.add(lblDni);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(165, 131, 44, 22);
		contentPane.add(lblEmail);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText(u.getName());
		textPane.setBounds(220, 42, 462, 22);
		contentPane.add(textPane);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setText(u.getDni());
		textPane_1.setBounds(218, 84, 464, 22);
		contentPane.add(textPane_1);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setText(GestorDB.emailBD(u.getDni()));
		textPane_2.setBounds(221, 131, 461, 22);
		contentPane.add(textPane_2);

	

		JButton btnNewButton = new JButton("Nueva Reserva");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VInicio.crearVInicio(u);
			}
		});
		btnNewButton.setBounds(351, 369, 120, 25);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cerrar Sesion");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				VLogin.crearVLogin();
			}
		});
		btnNewButton_1.setBounds(165, 369, 119, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnEliminarReserva = new JButton("Eliminar reserva");
		btnEliminarReserva.setForeground(Color.RED);
		btnEliminarReserva.setBounds(10, 370, 122, 23);
		contentPane.add(btnEliminarReserva);
		
		btnEliminarReserva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedReserva > 0) {
					GestorDB.delReserva(selectedReserva);
					JOptionPane.showMessageDialog(frame,
						    "Se reiniciará la ventana para que los cambios surtan efecto.",
						    "Aviso",
						    JOptionPane.WARNING_MESSAGE);
					VPerfil perfil = new VPerfil(u);
					perfil.setVisible(true);
					dispose();
				}else if (selectedReserva == -1) {
					JOptionPane.showMessageDialog(frame,
						    "No se pueden eliminar vuelos individuales de una reserva.",
						    "ERROR",
						    JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(frame,
						    "Debes seleccionar una reserva.",
						    "ERROR",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});



	}
}
