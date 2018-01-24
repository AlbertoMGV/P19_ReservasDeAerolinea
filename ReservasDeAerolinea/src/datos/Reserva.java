package datos;

import java.sql.Time;
import java.util.ArrayList;

public class Reserva {
	
	private int COD_R;
	private double precio;
	private String DNI;
	private String horasalida;
	private String fecha;
	
	
	
	
	
	@Override
	public String toString() {
		return "Nº de Reserva:" + COD_R + " | Precio (total):" + precio + " | Hora de Salida:"+horasalida+ " | Fecha de salida:"+fecha;
	}

	public Reserva(int cOD_R, double precio, String dNI, String horasalida, String fcha) {
		super();
		COD_R = cOD_R;
		this.precio = precio;
		DNI = dNI;
		this.horasalida=horasalida;
		fecha = fcha;
		
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHorasalida() {
		return horasalida;
	}

	public void setHorasalida(String horasalida) {
		this.horasalida = horasalida;
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
