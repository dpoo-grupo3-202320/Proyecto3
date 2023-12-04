package clases;

import java.io.Serializable;

public class Cliente extends Usuario implements Serializable {
	private static final long serialVersionUID = 3641464349295076139L;
	/**
	 * 
	 */
	private String nombres;
	private String numeroTelefono;
	private String direccion;
	private String fechaNacimiento;
	private String nacionalidad;
	private String imagenDocumentoIdentidad;
	private LicenciaDeConduccion licenciaDeConduccion;
	private TarjetaDeCredito tarjetaDeCredito;

	public Cliente(String usuario, String contraseña, String nombres, String numeroTelefono, String direccion,
			String fechaNacimiento, String nacionalidad, String imagenDocumentoIdentidad,
			LicenciaDeConduccion licenciaDeConduccion, TarjetaDeCredito tarjetaDeCredito) {
		super(usuario, contraseña, "Cliente");
		this.nombres = nombres;
		this.numeroTelefono = numeroTelefono;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.imagenDocumentoIdentidad = imagenDocumentoIdentidad;
		this.licenciaDeConduccion = licenciaDeConduccion;
		this.tarjetaDeCredito = tarjetaDeCredito;
	}

	/*
	 * getters
	 */

	public String getNombres() {
		return nombres;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getImagenDocumentoIdentidad() {
		return imagenDocumentoIdentidad;
	}

	public LicenciaDeConduccion getLicenciaDeConduccion() {
		return licenciaDeConduccion;
	}

	public TarjetaDeCredito getTarjetaDeCredito() {
		return tarjetaDeCredito;
	}

	/*
	 * setters
	 */

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setImagenDocumentoIdentidad(String imagenDocumentoIdentidad) {
		this.imagenDocumentoIdentidad = imagenDocumentoIdentidad;
	}

	public void setLicenciaDeConduccion(LicenciaDeConduccion licenciaDeConduccion) {
		this.licenciaDeConduccion = licenciaDeConduccion;
	}

	public void setTarjetaDeCredito(TarjetaDeCredito tarjetaDeCredito) {
		this.tarjetaDeCredito = tarjetaDeCredito;
	}
}
