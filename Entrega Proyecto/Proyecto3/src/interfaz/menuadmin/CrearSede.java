package interfaz.menuadmin;

import javax.swing.*;

import clases.SistemaAlquiler;
import interfaz.Navegador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



@SuppressWarnings("serial")
public class CrearSede extends JPanel{
	
	private JTextField nombreTextField;
    private JTextField ubicacionTextField;
    private JTextField hrAperturaTextField;
    private JTextField hrCierreTextField;

    
    private final SistemaAlquiler SA;
    private final Navegador nav;

    public CrearSede(final Navegador navegador, SistemaAlquiler sistemaAlquiler) {
    	this.nav = navegador;
    	this.SA = sistemaAlquiler;
        setLayout(new GridLayout(7, 2, 10, 10)); // 7 filas, 2 columnas

        // Etiquetas y campos de texto
        add(new JLabel("Nombre:"));
        nombreTextField = new JTextField();
        add(nombreTextField);

        add(new JLabel("Ubicacion:"));
        ubicacionTextField = new JTextField();
        add(ubicacionTextField);

        add(new JLabel("Horario Apertura:"));
        hrAperturaTextField = new JTextField();
        add(hrAperturaTextField);
        
        add(new JLabel("Horario Cierre:"));
        hrCierreTextField = new JTextField();
        add(hrCierreTextField);


        // Botón para agregar vehículo
        JButton agregarSedeButton = new JButton("Agregar Sede");
        agregarSedeButton.setBackground(Color.GREEN);
        agregarSedeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String nombre = nombreTextField.getText();
                String ubicacion = ubicacionTextField.getText();
                int hrsCSede = Integer.parseInt(hrAperturaTextField.getText());
                int hrsASede = Integer.parseInt(hrAperturaTextField.getText());
            

                try {
					SA.crearSede(nombre, ubicacion, hrsASede, hrsCSede);
					nav.paginaAnterior();
					nav.mensajeCliente("Nueva sede creada",2000);
				} catch (Exception ex2) {
					if (ex2 instanceof IllegalArgumentException) {
						nav.mensajeCliente("Rango no válido: " + ex2.getMessage(),2000);
					} else {
						nav.mensajeCliente(ex2.getMessage(), 2000);
					}
				}
            }
        });
        add(agregarSedeButton);
        
        JButton volverButton =  new JButton("Volver");
        volverButton.addActionListener(new ActionListener(){
        	@Override
            public void actionPerformed(ActionEvent e) {
        		nav.paginaAnterior();
        		}
        	
        });
    }

}
