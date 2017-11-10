package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Usuario;
import utilidades.LoginRegister;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.ImagingOpException;
import java.awt.event.ActionEvent;

public class VLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	
	public static void crearVLogin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VLogin frame = new VLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//creo la ventana en absolut layer ya que no  va a ser resizable :)
	
	public VLogin() {
		//cambiar look 
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(725, 350, 450, 300);
		this.setTitle("[DeustoAIR] Login");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("DNI:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblName.setBounds(27, 103, 95, 38);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("Pass:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPassword.setBounds(27, 171, 95, 38);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Calibri", Font.PLAIN, 20));
		textField.setBounds(134, 103, 278, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(134, 172, 278, 30);
		contentPane.add(passwordField);
		
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("././res/bg.jpg"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(27, 13, 385, 79);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VRegister.crearVRegister();
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnNewButton.setBounds(156, 227, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnLogin= new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = textField.getText();
				char[] pwd = passwordField.getPassword();
				
				String pass = "";
				
				for(int i = 0; i < pwd.length; i++){
					pass += pwd[i];
				}
				
				Usuario u = LoginRegister.log(dni, pass);
				
				if (u != null) {
					//launch ventana de app
					VInicio.crearVInicio(u);
					
					dispose();
					
				} else {
					//lanzar error de loging
					JOptionPane.showMessageDialog(null, "DNI o Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
				};
			}
		});
		btnLogin.setBounds(290, 226, 97, 25);
		contentPane.add(btnLogin);
		
		textField.setText("45915504Y");
		passwordField.setText("123qwe");
	}
}
