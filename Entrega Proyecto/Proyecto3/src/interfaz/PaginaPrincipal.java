package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import clases.SistemaAlquiler;

/**
 * El login se hace aca
 */
@SuppressWarnings("serial")
public class PaginaPrincipal extends JFrame {

	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;

	public PaginaPrincipal() throws FileNotFoundException, ClassNotFoundException, IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		// rosado
		setBackground(new Color(255, 192, 203));
		setOpacity(1f);
		sistemaAlquiler = new SistemaAlquiler();

		nav = new Navegador(sistemaAlquiler);
		add(nav, BorderLayout.CENTER);
	}

	@Override
	public void dispose() {

		super.dispose();
	}

	public static void main(String[] args) {
		try {
			PaginaPrincipal pp = new PaginaPrincipal();
			// pp.setExtendedState(JFrame.MAXIMIZED_BOTH);
			pp.setResizable(true);
			pp.setSize(new Dimension(500, 500));
			pp.setVisible(true);
			// pp.setUndecorated(true);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Carga de datos fallida");
			e.printStackTrace();
		}
	}
}
