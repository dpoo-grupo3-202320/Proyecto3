package interfaz.menucliente;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class advertenciaUsuario extends JFrame{
	
	public advertenciaUsuario() {
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel mensaje= new JLabel("No se encontró usuario asociado a dicha contraseña");
		mensaje.add(mensaje);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
