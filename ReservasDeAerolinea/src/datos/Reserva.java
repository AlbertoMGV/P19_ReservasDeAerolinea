package datos;

import java.sql.Time;
import java.util.ArrayList;

public class Reserva {
	
	private int COD_R;
	private double precio;
	private String DNI;

	
	
	
	
	
	@Override
	public String toString() {
		return "Numero Reserva:" + COD_R + ". Precio:" + precio+".";
	}

	public Reserva(int cOD_R, double precio, String dNI) {
		super();
		COD_R = cOD_R;
		this.precio = precio;
		DNI = dNI;
		
	}
	
	public Reserva() {
		
	}

	

	public int getCOD_R() {
		return COD_R;
	}
	public double getPrecio() {
		return precio;
	}
	public String getDNI() {
		return DNI;
	}
	public void setCOD_R(int i) {
		COD_R = i;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	
	

	
}
