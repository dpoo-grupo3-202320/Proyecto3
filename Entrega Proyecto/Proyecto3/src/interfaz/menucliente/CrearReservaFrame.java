package interfaz.menucliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.Cliente;
import clases.Range;
import clases.SistemaAlquiler;
import clases.Usuario;

public class CrearReservaFrame extends JFrame{
	
	public CrearReservaFrame (Usuario cliente, SistemaAlquiler SA) {
		
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setName("Reserva");
		
		panel.add(new JLabel("Categoria"));
		JTextField categoria = new JTextField();
		panel.add(categoria);
		
		panel.add(new JLabel("Fecha recogida"));
		JTextField fechaR = new JTextField("yyyy-MM-dd HH:mm");
		panel.add(fechaR);
		
		panel.add(new JLabel("Ubicacion recogida"));
		JTextField ubicacionR = new JTextField();
		panel.add(ubicacionR);
		
		panel.add(new JLabel("Ubicacion entrega"));
		JTextField ubicacionE = new JTextField();
		panel.add(ubicacionE);
		
		panel.add(new JLabel("Fecha Entrega"));
		JTextField fechaE = new JTextField("yyyy-MM-dd HH:mm");
		panel.add(fechaE);
		panel.add(new JLabel("A más tardar"));
		JTextField entregaTarde = new JTextField("yyyy-MM-dd HH:mm");
		panel.add(entregaTarde);
		
		JPanel panelReserva = new JPanel();
		panelReserva.setLayout(new BorderLayout());
		
		JButton reservar=new JButton("RESERVAR");
		panelReserva.add(reservar,BorderLayout.SOUTH);
		
		reservar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime fechaRecogida = LocalDateTime.parse(fechaR.getText(), formatter);
				LocalDateTime fechaEntregaTemprano = LocalDateTime.parse((CharSequence) fechaE, formatter);
				LocalDateTime fechaEntregaTarde = LocalDateTime.parse((CharSequence) entregaTarde, formatter);
				Range<LocalDateTime> rangoEntrega = new Range<LocalDateTime>(fechaEntregaTemprano,fechaEntregaTarde);
				
				SA.crearReserva(categoria.getText(), fechaRecogida,ubicacionR.getText(), ubicacionE.getText(), rangoEntrega, cliente, null);
				
			}
			
		});
		
	}

	

}
