package interfaz.menuadmin;

import javax.swing.*;

import clases.Sede;
import clases.SistemaAlquiler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AgregarVehiculo extends JPanel {
    private JTextField placaTextField;
    private JTextField marcaTextField;
    private JTextField colorTextField;
    private JComboBox<String> transmisionComboBox;
    private JComboBox<String> categoriaComboBox;
    private JComboBox<String> sedeComboBox;
    
    private final SistemaAlquiler SA;

    public AgregarVehiculo(SistemaAlquiler sistemaAlquiler) {
    	
    	this.SA = sistemaAlquiler;
        setLayout(new GridLayout(7, 2, 10, 10)); // 7 filas, 2 columnas

        // Etiquetas y campos de texto
        add(new JLabel("Placa:"));
        placaTextField = new JTextField();
        add(placaTextField);

        add(new JLabel("Marca:"));
        marcaTextField = new JTextField();
        add(marcaTextField);

        add(new JLabel("Color:"));
        colorTextField = new JTextField();
        add(colorTextField);

        // Etiquetas y combos
        add(new JLabel("Transmisión:"));
        String[] transmisionOptions = {"Automático", "Mecánico"};
        transmisionComboBox = new JComboBox<>(transmisionOptions);
        add(transmisionComboBox);

        add(new JLabel("Categoría:"));
        String[] categoriaOptions = {"SUV", "Pequeños", "Lujo", "Otros"};
        categoriaComboBox = new JComboBox<>(categoriaOptions);
        add(categoriaComboBox);
        
        add(new JLabel("Sede:"));
        ArrayList<Sede> sedes = SA.getSedes();
        String[] sedeOptions = sedes.stream().map(Sede::getNombre).toArray(String[]::new);
        sedeComboBox = new JComboBox<>(sedeOptions);
        add(sedeComboBox);

        // Botón para agregar vehículo
        JButton agregarVehiculoButton = new JButton("Agregar Vehículo");
        agregarVehiculoButton.setBackground(Color.GREEN);
        agregarVehiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener valores de los campos y combos
                String placa = placaTextField.getText();
                String marca = marcaTextField.getText();
                String color = colorTextField.getText();
                String transmision = (String) transmisionComboBox.getSelectedItem();
                String categoria = (String) categoriaComboBox.getSelectedItem();
                String sede = (String) sedeComboBox.getSelectedItem();

                // Llamar a la función agregarVehiculo del sistemaAlquiler
                try {
                    SA.agregarVehiculo(placa, marca, color, transmision, categoria, sede, "disponible");
                    System.out.println("Nuevo vehiculo creado");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        add(agregarVehiculoButton);
    }

}
