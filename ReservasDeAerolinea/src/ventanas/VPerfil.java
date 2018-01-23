package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import datos.Reserva;
import datos.Usuario;
import gestores.GestorDB;

public class VPerfil extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tbmperf;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(725, 350, 700, 442);
		setTitle("[DeustoAIR] Mi Perfil");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] columnNames = {"NºReserva", "Precio", "Dia", "Hora de Salida"};
		tbmperf = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		ArrayList<Reserva> lstrsv = GestorDB.listReservas(u);
		
		String cod_r;
		String precio;

		
		for (int i = 0; i < lstrsv.size()-1; i++) {
			cod_r="";
			precio="";
			cod_r = cod_r+ lstrsv.get(i).getCOD_R();
			precio = precio+lstrsv.get(i).getPrecio();
			
			String[] dts = {cod_r,precio,"1/1/2018","00:00:00"};
						
			tbmperf.addRow(dts);
		}
		
		//prueba añadir al vperfil reservas para ver como quedaria
		for (int i = 0; i < 10; i++) {
			String[] dts = {"cod"+i,"presio","1/1/2018","00:00:00"};			
			tbmperf.addRow(dts);
		}

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

		JTable listReservas = new JTable(tbmperf);		
		listReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JLabel lblNewLabel_2 = new JLabel("Reservas");
		lblNewLabel_2.setBounds(22, 208, 56, 16);
		contentPane.add(lblNewLabel_2);


		JScrollPane panelscroll = new JScrollPane(listReservas);
		panelscroll.setBounds(12, 241, 670, 111);
		contentPane.add(panelscroll);

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

















	}
}
