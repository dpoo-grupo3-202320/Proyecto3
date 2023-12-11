package interfaz.menuadmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import clases.SistemaAlquiler;
import clases.Sede;
import interfaz.Navegador;

@SuppressWarnings("serial")
public class InformacionSede extends JPanel {
	
	private JTextField nombreTextField;
	private JTextField hrASedeTextField;
	private JTextField hrCSedeTextField;
    
    
    private final SistemaAlquiler SA;
    private final Navegador nav;

    public InformacionSede(final Navegador navegador,SistemaAlquiler sistemaAlquiler, String nombre) throws Exception {
    	this.nav = navegador;
    	this.SA = sistemaAlquiler;
        setLayout(new GridLayout(1, 2, 10, 10)); // 1 fila, 2 columnas

        // Panel para la primera columna
        JPanel leftPanel = new JPanel(new GridLayout(7, 1, 10, 10)); // 7 filas, 1 columna
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaciado
        
        //Obtener vehiculo solicitado
        Sede laSede = SA.getSede(nombre);
        
        if (laSede != null)
        {
        	// Etiquetas y campos de texto
            leftPanel.add(new JLabel("Nombre: "));
            leftPanel.add(nombreTextField = new JTextField(laSede.getNombre()));
            
            
            leftPanel.add(new JLabel("Dirección: " + laSede.getUbicacion()));

            leftPanel.add(new JLabel("Horario Apertura:"));
            leftPanel.add(hrASedeTextField = new JTextField(String.valueOf(laSede.getHorariosDeAtencion().getHoras().getLow())));
            
            
            leftPanel.add(new JLabel("Horario Cierre:"));
            leftPanel.add(hrCSedeTextField = new JTextField(String.valueOf(laSede.getHorariosDeAtencion().getHoras().getHigh())));
         
        }
        else {
        	throw new Exception("No se encontró una sede con ese nombre");
        	
        }
        
     // Panel para la segunda columna
        JPanel rightPanel = new JPanel(new GridLayout(7, 1, 10, 10)); // 7 filas, 1 columna
        rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaciado
        
     // Botones
        
        JButton actualizarSedeButton = createGreenButton("Actualizar sede");
        actualizarSedeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try 
            	{
            		SA.modificarNombreSede(nombreTextField.getText(), nombre);
            		SA.modificarHorarioSede(nombre, Integer.parseInt(hrASedeTextField.getText()), Integer.parseInt(hrCSedeTextField.getText()) );
            		
            	}
            	catch (Exception ex) 
            	{
            		nav.mensajeCliente(ex.getMessage(), 2500);
            	}
               nav.paginaAnterior();
            }
        });
        
        
        JButton finalizarConsultaButton = createGreenButton("Finalizar Consulta");
        finalizarConsultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               nav.paginaAnterior();
            }
        });
        
        rightPanel.add(actualizarSedeButton);
        rightPanel.add(finalizarConsultaButton);

        // Agregar los dos paneles al panel principal
        add(leftPanel);
        add(rightPanel); 
               
    }
    
    private JButton createGreenButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setBackground(Color.GREEN);
        return button;
    }
    
}

