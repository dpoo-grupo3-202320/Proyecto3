package interfaz.menuadmin;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import clases.Admin;
import clases.SistemaAlquiler;
import interfaz.Navegador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuAdmin extends JPanel {

    private final Navegador nav;
    private final SistemaAlquiler sistemaAlquiler;
    private final Admin admin;

    public MenuAdmin(Navegador nav, SistemaAlquiler sistemaAlquiler, Admin adminActual) {
        this.nav = nav;
        this.admin = adminActual;
        this.sistemaAlquiler = sistemaAlquiler;
        setLayout(new BorderLayout());

        // Etiqueta del título
        JLabel titleLabel = new JLabel("Menu Administrador", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 16));

        // Panel para los botones con un GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());

        // Etiqueta de opciones
        JLabel optionsLabel = new JLabel("Opciones", SwingConstants.CENTER);

        // Añadir la etiqueta al panel de botones
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.insets = new Insets(0, 0, 10, 0); // Márgenes para separar la etiqueta de los botones
        buttonPanel.add(optionsLabel, labelConstraints);

        // Agregar botones según el tipo de usuario
        if (admin.getSede() == null) {
            addButton(buttonPanel, "Agregar Vehiculo", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nav.agregarPagina(new AgregarVehiculo(nav, sistemaAlquiler));
                }
            });

            addButton(buttonPanel, "Crear Sede", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nav.agregarPagina(new CrearSede(nav,sistemaAlquiler));
                }
            });

            addButton(buttonPanel, "Modificar Sede", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // nav.agregarPagina(new AgregarVehiculo());
                }
            });
            addButton(buttonPanel, "Consultar informacion vehiculo", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentanaPlaca();
                }
            });
            addButton(buttonPanel, "Cerrar Sesión", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nav.cerrarSesion();
                }
            });

        } else {

            addButton(buttonPanel, "Añadir Empleado", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // nav.agregarPagina(new AgregarVehiculo());
                }
            });
            addButton(buttonPanel, "Eliminar Empleado", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // nav.agregarPagina(new AgregarVehiculo());
                }
            });
            addButton(buttonPanel, "Consultar Informacion Vehiculo", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // nav.agregarPagina(new AgregarVehiculo());
                }
            });
            addButton(buttonPanel, "Cerrar Sesión", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // nav.agregarPagina(new AgregarVehiculo());
                }
            });

        }

        // Agregar componentes al panel principal
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void addButton(JPanel panel, String buttonText, ActionListener actionListener) {
        JButton button = new JButton(buttonText);
        button.setPreferredSize(new Dimension(200, 40)); // Establecer un tamaño preferido
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = panel.getComponentCount();
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        button.addActionListener(actionListener); // Agregar el ActionListener al botón
        panel.add(button, buttonConstraints);
    }

    private void mostrarVentanaPlaca() {
        JTextField placaTextField = new JTextField();
        Object[] message = { "Placa:", placaTextField };

        int option = JOptionPane.showOptionDialog(null, message, "Consultar Información Vehículo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            String placa = placaTextField.getText();
            try {
            nav.agregarPagina(new InformacionVehiculo(nav, sistemaAlquiler, placa));
            }
            catch (Exception e) {
            nav.mensajeCliente(e.getMessage(), 2500);
            }
        }
    }

}
