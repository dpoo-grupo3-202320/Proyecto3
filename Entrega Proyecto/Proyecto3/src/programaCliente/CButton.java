package programaCliente;

import java.awt.Image;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CButton extends JButton{
	
	public CButton (String ruta) {
		
		
		ImageIcon icon= new ImageIcon(ruta);
		Image imagenEscalada = icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), this.CENTER);
		ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
		
		this.setIcon(iconoEscalado);
		
		
	}

}
