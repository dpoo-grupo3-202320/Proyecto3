package interfaz.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MostrarYEscogerImagen extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8295618353242111600L;
	private File image;
	private TButton boton;

	public MostrarYEscogerImagen(String texto) {
		setLayout(new BorderLayout());
		JLabel label = new JLabel();
		boton = new TButton(texto, () -> {
			final JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("jpg", "jpeg", "png"));
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				image = fc.getSelectedFile();
				BufferedImage buffered = ImageIO.read(image);
				Image resized = buffered.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
				label.setIcon(new ImageIcon(resized));
			}
			establecerColorBoton();
			return null;
		});
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		establecerColorBoton();
		add(label, BorderLayout.CENTER);
		add(boton, BorderLayout.SOUTH);
	}

	public File getImage() {
		return image;
	}

	private void establecerColorBoton() {
		if (image == null) {
			// si imagen no escogida, rojo
			boton.setBackground(new Color(237, 154, 154));
		} else {
			// si imagen escogida, verde
			boton.setBackground(new Color(203, 230, 201));
		}
	}
}
