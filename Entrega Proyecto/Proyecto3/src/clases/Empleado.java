package clases;

public class Empleado extends Usuario {
	private static final long serialVersionUID = 4103466959519619035L;
	
	private Sede sede;

	public Empleado(String usuario, String contraseña, Sede sede) {
		super(usuario, contraseña, "Empleado");
		this.sede = sede;
	}

	public Sede getSede() {
		
		return sede;
	}

}
