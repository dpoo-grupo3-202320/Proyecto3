package interfaz.menucliente;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class IngresarTarjeta extends JFrame {
	
	public IngresarTarjeta () {
		
		JPanel panel = new JPanel(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		panel.add(new JLabel("Numero"));
		JTextField numero= new JTextField();
		numero.setEditable(true);
		panel.add(numero);
		
		panel.add(new JLabel("Fecha Vencimiento"));
		JTextField vence= new JTextField();
		vence.setText("MM/AA");
		vence.setEditable(true);
		panel.add(vence);
		
		panel.add(new JLabel("CVV"));
		JPasswordField contraseña= new JPasswordField();
		panel.add(contraseña);
	}

}
