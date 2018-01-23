package datos;

import java.util.ArrayList;

public class Reserva {
	
	private String COD_R;
	private double precio;
	private String DNI;
	
	
	
	
	
	public Reserva(String cOD_R, double precio, String dNI) {
		super();
		COD_R = cOD_R;
		this.precio = precio;
		DNI = dNI;
		
	}
	
	public String getCOD_R() {
		return COD_R;
	}
	public double getPrecio() {
		return precio;
	}
	public String getDNI() {
		return DNI;
	}
	public void setCOD_R(String cOD_R) {
		COD_R = cOD_R;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	
	

	
}
