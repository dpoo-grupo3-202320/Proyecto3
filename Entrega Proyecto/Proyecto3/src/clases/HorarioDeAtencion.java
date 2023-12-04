package clases;

import java.io.Serializable;

public class HorarioDeAtencion implements Serializable {
	private static final long serialVersionUID = 6340839134383635203L;
	/**
	 * 
	 */
	private Range<Integer> horas;

	public HorarioDeAtencion(Range<Integer> horas) {
		this.horas = horas;
	}

	public Range<Integer> getHoras() {
		return this.horas;
	}
	public void setHoras(Range<Integer> hora){
		this.horas = hora;
	}
}
