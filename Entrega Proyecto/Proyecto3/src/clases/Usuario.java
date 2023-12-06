package clases;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
	private static final long serialVersionUID = 2531807322318657943L;
	/**
	 * 
	 */
	protected String usuario;
	protected String contraseña;
	protected String tipo;

		public Usuario(String usuario, String contraseña, String tipo){
			this.usuario = usuario;
			this.contraseña = contraseña;
			this.tipo = tipo;
		}


	//Getters
	public String getUsuario() {
		return this.usuario;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public String getTipo() {
		return this.tipo;
	}
	
	//setters

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
