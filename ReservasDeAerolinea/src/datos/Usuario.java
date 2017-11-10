package datos;

public class Usuario {
	
	private String dni;
	private String name;
	private String email;
	
	public Usuario(String dni, String name, String email){
		this.dni = dni;
		this.name = name;
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
