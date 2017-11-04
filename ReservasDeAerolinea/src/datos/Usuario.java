package datos;

public class Usuario {
	
	private String dni;
	private String name;
	
	public Usuario(String dni, String name){
		this.dni = dni;
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public String getName() {
		return name;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
