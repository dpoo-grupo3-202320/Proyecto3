package interfaz.componentes;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class TMensajeUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private static Boolean spamProtect = false;

	public TMensajeUsuario(JComponent positionReference, String toastString, int millis) {
		if (spamProtect) {
			return;
		}
		setUndecorated(true);
		setAlwaysOnTop(true);
		setFocusableWindowState(false);
		setLayout(new GridBagLayout());

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panel.setBackground(new Color(160, 160, 160));
		JLabel toastLabel = new JLabel(toastString);
		toastLabel.setForeground(Color.WHITE);
		panel.add(toastLabel);
		add(panel);
		pack();

		Window window = SwingUtilities.getWindowAncestor(positionReference);
		int xcoord = window.getLocationOnScreen().x + window.getWidth() / 2 - getWidth() / 2;
		int ycoord = window.getLocationOnScreen().y + (int) ((double) window.getHeight()) - 35 - getHeight();
		setLocation(xcoord, ycoord);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
		setVisible(true);

		new Thread() {
			public void run() {
				try {
					spamProtect = true;
					Thread.sleep(millis);
					dispose();
					spamProtect = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}