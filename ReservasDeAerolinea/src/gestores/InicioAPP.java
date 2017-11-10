package gestores;

import javax.swing.JOptionPane;

import ventanas.VLogin;

public class InicioAPP {

	public static void main(String[] args) {
		VLogin.crearVLogin();
		JOptionPane.showMessageDialog(null, "Aplicacion en desarrollo, no tener en cuenta bugs <3", "   Aviso!", JOptionPane.NO_OPTION);
	}

}
