package interfaz.menucliente;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import clases.ContenedorDeDatos;
import clases.SistemaAlquiler;

public class MenuCliente extends JPanel {
	private static final long serialVersionUID = -4627965134321784440L;

	public MenuCliente(SistemaAlquiler SA) {

		JLabel titleLabel = new JLabel("Menu Cliente", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 16));

		JPanel buttonPanel = new JPanel();
		setLayout(new GridLayout(0, 1));

		JButton inicio;
		JButton registro;
		JButton salir;
		add(inicio = new JButton("Iniciar Sesion"));
		add(registro = new JButton("Registrarse"));
		add(salir = new JButton("Salir"));

		inicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//
				SesionCliente sesion = new SesionCliente(SA.getContenedorDeDatos(), SA);
			}
		});

		registro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//
				RegistroCliente registro = new RegistroCliente();
			}
		});

		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//
				System.exit(0);
			}
		});
	}

}
