package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilidades.LoginRegister;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class VRegister extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_2;

	public static void crearVRegister() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VRegister frame = new VRegister();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public VRegister() {
		//cambiar look 

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(725, 350, 450, 371);
		setTitle("[DeustoAIR] Register");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("././res/bg.jpg"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 13, 408, 62);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(22, 88, 69, 22);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(98, 88, 289, 22);
		contentPane.add(textField);
		textField.setToolTipText("Introduce tu DNI");
		textField.setColumns(10);

		JLabel lblName = new JLabel("  Name:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(12, 123, 89, 22);
		contentPane.add(lblName);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(98, 123, 289, 22);
		textField_1.setToolTipText("Introduce tu Nombre y Apellido");
		contentPane.add(textField_1);

		JLabel lblPass = new JLabel("Pass:");
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setBounds(22, 193, 69, 22);
		contentPane.add(lblPass);

		JLabel lblRepitPass = new JLabel("Repit pass:");
		lblRepitPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblRepitPass.setBounds(22, 228, 69, 22);
		contentPane.add(lblRepitPass);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = textField.getText();
				String user= textField_1.getText();
				String email= textField_2.getText();				
				char[] pwd1 = passwordField.getPassword();
				String pass1 = "";
				for(int i = 0; i < pwd1.length; i++){
					pass1 += pwd1[i];
				}				
				char[] pwd2 = passwordField_1.getPassword();
				String pass2 = "";
				for(int i = 0; i < pwd2.length; i++){
					pass2 += pwd2[i];
				}
				if (LoginRegister.reg(dni, user, pass1, pass2, email)==true) {
					dispose();
					VLogin.crearVLogin();
				} else {
					JOptionPane.showMessageDialog(null, "Los campos no cumplen los formatos", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnRegister.setBounds(268, 286, 97, 25);
		contentPane.add(btnRegister);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VLogin.crearVLogin();
			}
		});
		btnBack.setBounds(141, 286, 97, 25);
		contentPane.add(btnBack);

		passwordField = new JPasswordField();
		passwordField.setBounds(98, 193, 289, 22);
		passwordField.setToolTipText("Introduce una contraseña de entre 6 y 15 caracteres alfanumericos :)");
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(98, 228, 289, 22);
		passwordField_1.setToolTipText("Repite la contraseña");
		contentPane.add(passwordField_1);

		JLabel label = new JLabel("Email:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBounds(22, 158, 69, 22);
		contentPane.add(label);

		textField_2 = new JTextField();
		textField_2.setToolTipText("Introduce un email valido");
		textField_2.setColumns(10);
		textField_2.setBounds(98, 158, 289, 22);
		contentPane.add(textField_2);
	}
}
