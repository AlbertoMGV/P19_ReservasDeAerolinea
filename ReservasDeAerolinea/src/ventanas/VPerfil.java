package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Usuario;
import gestores.GestorDB;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JList;

public class VPerfil extends JFrame {

	private JPanel contentPane;

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
		setBounds(725, 350, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		textPane.setBounds(220, 42, 432, 22);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setText(u.getDni());
		textPane_1.setBounds(218, 84, 434, 22);
		contentPane.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setText(GestorDB.emailBD(u.getDni()));
		textPane_2.setBounds(221, 131, 431, 22);
		contentPane.add(textPane_2);
		
		JLabel lblNewLabel_2 = new JLabel("Reservas");
		lblNewLabel_2.setBounds(22, 208, 56, 16);
		contentPane.add(lblNewLabel_2);
	}
}
