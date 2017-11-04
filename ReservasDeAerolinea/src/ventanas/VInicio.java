package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Usuario;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class VInicio extends JFrame {
	
	private Usuario loggedUser;

	private JPanel contentPane;
	
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblWelcome = new JLabel("Bienvenido, " + u.getName());
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWelcome, BorderLayout.CENTER);
	}

}
