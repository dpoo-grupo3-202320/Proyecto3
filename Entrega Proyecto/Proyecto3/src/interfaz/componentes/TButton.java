package interfaz.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.border.Border;

public class TButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2198056323784232930L;

	public TButton(String texto, Callable<Void> action) {
		Font f = getFont();
		f = f.deriveFont(Font.BOLD);
		f = f.deriveFont(20f);

		setFont(f);
		// color fondo
		setBackground(new Color(255, 255, 255));
		// color texto
		setForeground(new Color(0, 0, 0));
		setText(texto);
		setMargin(new Insets(25, 25, 25, 25));
		addActionListener(e -> {
			try {
				action.call();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
}
