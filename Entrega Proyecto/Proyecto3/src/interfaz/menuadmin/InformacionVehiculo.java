package interfaz.menuadmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import clases.SistemaAlquiler;
import clases.Vehiculo;
import interfaz.Navegador;

@SuppressWarnings("serial")
public class InformacionVehiculo extends JPanel {
    private JLabel placaLabel;
    private JLabel marcaLabel;
    private JLabel colorLabel;
    private JLabel ubicacionLabel;
    private JLabel estadoLabel;
    private JLabel transmisionLabel;
    private JLabel categoriaLabel;
    
    private final SistemaAlquiler SA;
    private final Navegador nav;

    public InformacionVehiculo(final Navegador navegador,SistemaAlquiler sistemaAlquiler, String placa) throws Exception {
    	this.nav = navegador;
    	this.SA = sistemaAlquiler;
        setLayout(new GridLayout(1, 2, 10, 10)); // 1 fila, 2 columnas

        // Panel para la primera columna
        JPanel leftPanel = new JPanel(new GridLayout(7, 1, 10, 10)); // 7 filas, 1 columna
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaciado
        
        //Obtener vehiculo solicitado
        Vehiculo elVehiculo = SA.getVehiculo(placa);
        
        if (elVehiculo != null) {

        placaLabel = new JLabel("Placa: " + elVehiculo.getPlaca());
        marcaLabel = new JLabel("Marca: " + elVehiculo.getMarca());
        colorLabel = new JLabel("Color: " + elVehiculo.getColor());
        ubicacionLabel = new JLabel("Ubicación: " + elVehiculo.getUbicacion());
        estadoLabel = new JLabel("Estado: " + elVehiculo.getEstado());
        transmisionLabel = new JLabel("Transmisión: " + elVehiculo.getTransmision());
        categoriaLabel = new JLabel("Categoría: " +elVehiculo.getCategoria());

        leftPanel.add(placaLabel);
        leftPanel.add(marcaLabel);
        leftPanel.add(colorLabel);
        leftPanel.add(ubicacionLabel);
        leftPanel.add(estadoLabel);
        leftPanel.add(transmisionLabel);
        leftPanel.add(categoriaLabel);
        }
        else {
        	throw new Exception("No se encontró un vehiculo con la placa ingresada");
        	
        }

        // Panel para la segunda columna
        JPanel rightPanel = new JPanel(new GridLayout(7, 1, 10, 10)); // 7 filas, 1 columna
        rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaciado

        // Botones
        JButton consultarHistorialButton = createGreenButton("Consultar Historial");
        
        //TODO implementar consulta historial
        
        
        JButton finalizarConsultaButton = createGreenButton("Finalizar Consulta");
        finalizarConsultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               nav.paginaAnterior();
            }
        });
        
        
        JButton darDeAltaButton = createRedButton("Dar de Alta");
        darDeAltaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            	SA.eliminarVehiculo(placa);
            	}
            	catch (Exception ex) {
            		nav.mensajeCliente(ex.getMessage(), 2500);            		
            	}
               nav.paginaAnterior();
            }
        });
        

        rightPanel.add(consultarHistorialButton);
        rightPanel.add(finalizarConsultaButton);
        rightPanel.add(darDeAltaButton);

        // Agregar los dos paneles al panel principal
        add(leftPanel);
        add(rightPanel);
    }

    private JButton createGreenButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setBackground(Color.GREEN);
        return button;
    }

    private JButton createRedButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setBackground(Color.RED);
        return button;
    }

}
