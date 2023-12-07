package clases;

public class Admin extends Usuario {
	private static final long serialVersionUID = 2699912451110887949L;
	/**
	 * 
	 */
	protected String sede = null;

	// Constructores: existe uno para el admin general, que solo sea usa la primera
	// vez que se corre la aplicacion. Y otro para definir admins locales.

	public Admin(String usuario, String contraseña) {
		super(usuario, contraseña, "Administrador");
	}

	public Admin(String usuario, String contraseña, String sede) {
		super(usuario, contraseña, "Administrador");
		this.sede = sede;
	}

	/*
	 * getters
	 */
	public String getNombreUsuario() {
		return this.usuario;
	}

	public String getClave() {
		return this.contraseña;
	}

	public String getSede() {
		return this.sede;
	}

	/*
	 * setters
	 */

	public void setUsuario(String nombreUsuario) {
		this.usuario = nombreUsuario;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}
}
