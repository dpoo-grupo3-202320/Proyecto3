package interfaz.menucliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clases.Cliente;
import clases.ContenedorDeDatos;
import clases.SistemaAlquiler;
import clases.Usuario;

public class SesionCliente extends JFrame {
	
	ContenedorDeDatos CD;
	
	public  SesionCliente(ContenedorDeDatos CD,SistemaAlquiler SA) {
		
		this.CD=CD;
		
		JPanel panel= new JPanel();
		panel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel.add(new JLabel("Usuario"));
		JTextField usuario= new JTextField();
		panel.add(usuario);
		
		panel.add(new JLabel("Contraseña"));
		JPasswordField contraseña= new JPasswordField();
		panel.add(contraseña);
		
		JPanel cerrar = new JPanel();
		JButton ingresar= new JButton("INGRESAR");
		cerrar.add(ingresar);
		
		ingresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 
				String user=usuario.getText();
				char[] password=contraseña.getPassword();
				
				String spassword = null;
				for(char i:password) {
					spassword+=String.valueOf(i);
				}
				
				Usuario logInUser= CD.getUsuario(user, spassword);
				
				if (logInUser==null) {
					advertenciaUsuario lookOut= new advertenciaUsuario();
				}else {
					LoggedInMenu menu= new LoggedInMenu(LogInUser,SA);
				}
			}});
	}

}
