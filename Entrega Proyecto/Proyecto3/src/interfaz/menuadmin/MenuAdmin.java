package interfaz.menuadmin;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import clases.Admin;
import clases.Sede;
import clases.SistemaAlquiler;
import interfaz.Navegador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
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
                    mostrarVentanaSede();
                }
            });
            addButton(buttonPanel, "Crear Seguro", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentanaSeguro();
                }
            });
            addButton(buttonPanel, "Modificar Seguro", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentanaElimSeguro();
                }
            });
            addButton(buttonPanel, "Consultar informacion vehiculo", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentanaPlaca(true);
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
                    nav.agregarPagina(new AgregarEmpleado(nav, sistemaAlquiler, admin.getSede()));
                }
            });
            addButton(buttonPanel, "Eliminar Empleado", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentanaUsuario();
                }
            });
            addButton(buttonPanel, "Consultar Informacion Vehiculo", new ActionListener() {
            	 @Override
                 public void actionPerformed(ActionEvent e) {
                     mostrarVentanaPlaca(false);
                 }
            });
            addButton(buttonPanel, "Cerrar Sesión", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	nav.cerrarSesion();
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

    private void mostrarVentanaPlaca(boolean flag) {
        JTextField placaTextField = new JTextField();
        Object[] message = { "Placa:", placaTextField };

        int option = JOptionPane.showOptionDialog(null, message, "Consultar Información Vehículo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            String placa = placaTextField.getText();
            try {
            nav.agregarPagina(new InformacionVehiculo(nav, sistemaAlquiler, placa, flag));
            }
            catch (Exception e) {
            nav.mensajeCliente(e.getMessage(), 2500);
            }
        }
    }
    
    private void mostrarVentanaSede() {
        // Crear un JComboBox con las sedes disponibles
        JComboBox<String> sedesComboBox = new JComboBox<>(obtenerNombresSedes());

        // Crear un panel para alinear los componentes
        JPanel panel = new JPanel();
        panel.add(new JLabel("Seleccione Sede:"));
        panel.add(sedesComboBox);

        int option = JOptionPane.showOptionDialog(null, panel, "Consultar Información Vehículo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            String sedeSeleccionada = (String) sedesComboBox.getSelectedItem();
            try {
                nav.agregarPagina(new InformacionSede(nav, sistemaAlquiler, sedeSeleccionada));
            } catch (Exception e) {
                nav.mensajeCliente(e.getMessage(), 2500);
            }
        }
    }

    private String[] obtenerNombresSedes() {
        // Obtener las sedes disponibles del sistemaAlquiler y convertirlas a un array de Strings
        ArrayList<Sede> sedes = sistemaAlquiler.getSedes();
        String[] nombresSedes = new String[sedes.size()];
        for (int i = 0; i < sedes.size(); i++) {
            nombresSedes[i] = sedes.get(i).getNombre();
        }
        return nombresSedes;
    }
    
    private void mostrarVentanaUsuario() {
        JTextField usuarioTextField = new JTextField();
        Object[] message = { "Usuario:", usuarioTextField };

        int option = JOptionPane.showOptionDialog(null, message, "Eliminar usuario",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            String usuario = usuarioTextField.getText();
            try {
				sistemaAlquiler.eliminarEmpleado(usuario);
				nav.mensajeCliente("Empleado eliminado",2500);
			} catch (Exception e) {
				nav.mensajeCliente(e.getMessage(),2500);
			}
        }
    }
    
    private void mostrarVentanaSeguro() {
        JTextField nombreSeguroTextField = new JTextField();
        JTextField costoDiarioTextField = new JTextField();

        // Crear un panel para alinear los componentes
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Nombre Seguro:"));
        panel.add(nombreSeguroTextField);
        panel.add(new JLabel("Costo Diario:"));
        panel.add(costoDiarioTextField);

        int option = JOptionPane.showOptionDialog(null, panel, "Agregar Usuario",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            String nomSeguro = nombreSeguroTextField.getText();
            Float costoDiario = Float.parseFloat(costoDiarioTextField.getText());
            try {
				sistemaAlquiler.agregarSeguro(nomSeguro, costoDiario);
				} 
             catch (NumberFormatException e) {
                nav.mensajeCliente("El costo diario debe ser un número válido.", 2500);
            } catch (Exception e) {
                nav.mensajeCliente(e.getMessage(), 2500);
            }
        }
    }
    
    private void mostrarVentanaElimSeguro() {
        JTextField nombreSeguroTextField = new JTextField();
        JTextField costoDiarioTextField = new JTextField();

        // Crear un panel para alinear los componentes
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Seguro a modificar:"));
        panel.add(nombreSeguroTextField);
        panel.add(new JLabel("Nuevo costo Diario:"));
        panel.add(costoDiarioTextField);

        int option = JOptionPane.showOptionDialog(null, panel, "Agregar Usuario",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            String nomSeguro = nombreSeguroTextField.getText();
            Float costoDiario = Float.parseFloat(costoDiarioTextField.getText());
            try {
				
				sistemaAlquiler.modificarValorSeguro(nomSeguro, costoDiario);
				} 
             catch (NumberFormatException e) {
                nav.mensajeCliente("El costo diario debe ser un número válido.", 2500);
            } catch (Exception e) {
                nav.mensajeCliente(e.getMessage(), 2500);
            }
        }
    }

}
