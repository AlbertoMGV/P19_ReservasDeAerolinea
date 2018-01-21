package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Airport;
import datos.Vuelo;
import gestores.GestorDB;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class VInfo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vuelo ejemplo = new Vuelo("XX-XXXX", Airport.get("BIO"), Airport.get("MAD"), GestorDB.getAircraft("BIO", "MAD", "UX"));
					Vuelo ejemplo2 = new Vuelo("XX-XXXX", Airport.get("BIO"), Airport.get("MAD"), GestorDB.getAircraft("BIO", "MAD", "UX"));

					Vuelo[] vuelos = {ejemplo, ejemplo2};
					VInfo frame = new VInfo(vuelos);
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
	public VInfo(Vuelo[] vuelos) {
		//cambiar look 

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel titlePanel = new JPanel();
		contentPane.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("Informaci\u00F3n sobre el vuelo elegido:");
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		titlePanel.add(lblTitle);
		
		JPanel flightsPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(flightsPanel);
		//contentPane.add(flightsPanel, BorderLayout.CENTER);
		flightsPanel.setLayout(new BoxLayout(flightsPanel, BoxLayout.Y_AXIS));
		
		for(Vuelo v : vuelos) {
			JPanel panel = v.getJPanel();
			flightsPanel.add(panel, BorderLayout.CENTER);
			flightsPanel.add(Box.createVerticalStrut(5));
		}
		
		contentPane.add(scroll);
	}
}
