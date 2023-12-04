package interfaz.menuadmin;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class InformacionVehiculo extends JPanel {
    private JLabel placaLabel;
    private JLabel marcaLabel;
    private JLabel colorLabel;
    private JLabel ubicacionLabel;
    private JLabel estadoLabel;
    private JLabel transmisionLabel;
    private JLabel categoriaLabel;

    public InformacionVehiculo() {
        setLayout(new GridLayout(1, 2, 10, 10)); // 1 fila, 2 columnas

        // Panel para la primera columna
        JPanel leftPanel = new JPanel(new GridLayout(7, 1, 10, 10)); // 7 filas, 1 columna
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaciado

        placaLabel = new JLabel("Placa: ");
        marcaLabel = new JLabel("Marca: ");
        colorLabel = new JLabel("Color: ");
        ubicacionLabel = new JLabel("Ubicación: ");
        estadoLabel = new JLabel("Estado: ");
        transmisionLabel = new JLabel("Transmisión: ");
        categoriaLabel = new JLabel("Categoría: ");

        leftPanel.add(placaLabel);
        leftPanel.add(marcaLabel);
        leftPanel.add(colorLabel);
        leftPanel.add(ubicacionLabel);
        leftPanel.add(estadoLabel);
        leftPanel.add(transmisionLabel);
        leftPanel.add(categoriaLabel);

        // Panel para la segunda columna
        JPanel rightPanel = new JPanel(new GridLayout(7, 1, 10, 10)); // 7 filas, 1 columna
        rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaciado

        // Botones
        JButton consultarHistorialButton = createGreenButton("Consultar Historial");
        JButton finalizarConsultaButton = createGreenButton("Finalizar Consulta");
        JButton darDeAltaButton = createRedButton("Dar de Alta");

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
