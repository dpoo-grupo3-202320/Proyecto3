package interfaz.menuadmin;

import javax.swing.*;

import clases.SistemaAlquiler;
import interfaz.Navegador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class AgregarEmpleado extends JPanel {
    private JTextField nombreTextField;
    private JTextField claveTextField;
    
    private final SistemaAlquiler SA;
    private final Navegador nav;

    public AgregarEmpleado(final Navegador navegador, SistemaAlquiler sistemaAlquiler, String sedeAdmin) {
    	this.nav = navegador;
    	this.SA = sistemaAlquiler;
        setLayout(new GridLayout(7, 2, 10, 10)); // 7 filas, 2 columnas

        // Etiquetas y campos de texto
        add(new JLabel("Usuario:"));
        nombreTextField = new JTextField();
        add(nombreTextField);

        add(new JLabel("Contraseña:"));
        claveTextField = new JTextField();
        add(claveTextField);

        

        // Botón para agregar Empleado
        JButton agregarEmpleadoButton = new JButton("Agregar Empleado");
        agregarEmpleadoButton.setBackground(Color.GREEN);
        agregarEmpleadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener valores de los campos y combos
                String usuario = nombreTextField.getText();
                String clave = claveTextField.getText();

                // Llamar a la función agregarVehiculo del sistemaAlquiler
                try {
                    SA.registroEmpleado(usuario, clave, SA.getSede(sedeAdmin));
                    nav.mensajeCliente("Empleado registrado a la sede: " + sedeAdmin, 3500);
                    nav.paginaAnterior();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        add(agregarEmpleadoButton);
    }

}
