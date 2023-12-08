package interfaz.componentes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageFilter;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MostrarYEscogerImagen extends JPanel {
  private File image;

  public MostrarYEscogerImagen(String texto) {
    setLayout(new BorderLayout());
    JLabel label = new JLabel();
    add(label, BorderLayout.CENTER);
    add(new TButton(texto, () -> {
      final JFileChooser fc = new JFileChooser();
      fc.setFileFilter(new FileNameExtensionFilter("jpg", "jpeg", "png"));
      int returnVal = fc.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        image = fc.getSelectedFile();
        label.setIcon(new ImageIcon(image.getPath()));
      }
      return null;
    }), BorderLayout.SOUTH);
  }

  public File getImage() {
    return image;
  }
}
