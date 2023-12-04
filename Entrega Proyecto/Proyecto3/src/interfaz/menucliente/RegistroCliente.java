package interfaz.menucliente;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistroCliente extends JFrame  {
	
	public RegistroCliente() {
		
		JPanel panel1= new JPanel();
		panel1.setLayout(new GridLayout(14,1));
		
		panel1.add(new JLabel("Nombre: "));
		JTextField nombre= new JTextField();
		nombre.setEditable(true);
		panel1.add(nombre);
		
		panel1.add(new JLabel("Numero de telefono: "));
		JTextField numero= new JTextField();
		numero.setEditable(true);
		panel1.add(numero);
		
		panel1.add(new JLabel("Direccion: "));
		JTextField direccion= new JTextField();
		direccion.setEditable(true);
		panel1.add(direccion);
		
		panel1.add(new JLabel("Correo: "));
		JTextField correo= new JTextField();
		correo.setEditable(true);
		panel1.add(correo);
		
		panel1.add(new JLabel("Licencia de conduccion: "));
		JButton licencia= new JButton("subir licencia");
		JTextField textField = new JTextField();
		JLabel imageLabel = new JLabel();
		
		licencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccionar Imagen");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(RegistroCliente.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String imagePath = selectedFile.getAbsolutePath();
                    textField.setText(imagePath);

                    
                    ImageIcon icon = new ImageIcon(imagePath);
                    imageLabel.setIcon(icon);
                }
            }

			
        });

		
		panel1.add(textField);
		panel1.add(licencia);
		panel1.add(imageLabel);
		
		panel1.add(new JLabel("Cedula: "));
		JTextField cedula= new JTextField();
		cedula.setEditable(true);
		panel1.add(nombre);
		
		panel1.add(new JLabel("Tarjeta: "));
		JButton tarjeta= new JButton("Tarjeta de Credito");
		tarjeta.addActionListener(new ActionListener() {
			 @Override
	            public void actionPerformed(ActionEvent e) {
	                new IngresarTarjeta();
			 }
		});
		
		panel1.add(tarjeta);
		
		JPanel panel2= new JPanel();
		JButton volver= new JButton("Volver");
		panel2.add(volver);
		
		
	}

}
