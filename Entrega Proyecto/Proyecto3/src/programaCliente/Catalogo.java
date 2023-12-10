package programaCliente;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import clases.Inventario;
import clases.SistemaAlquiler;
import clases.Vehiculo;
import interfaz.componentes.TButton;
import interfaz.registro.IniciarSesion;
import interfaz.registro.RegistrarCliente;
import interfaz.registro.RegistrarEmpleado;



public class Catalogo extends JFrame{

	private DefaultListModel<String> modeloLista;
    private JList<String> listaVehiculos;
    
    public Catalogo(NavegadorApp nav, GestorDatos gestor) {
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
    	setSize(300, 200);
    	setLayout(new GridLayout ());
    	
    	ArrayList<Vehiculo> inventario= gestor.getInventario();
    	
    	modeloLista = new DefaultListModel<>();
        for (Vehiculo vehiculo : inventario) {
            modeloLista.addElement(vehiculo.toString());
        }
        
        ArrayList<String> rutas = leerCSV("./Persistencia/imagenes.csv");
        
        listaVehiculos = new JList<>(modeloLista);
        int i=0;
        
        while (i< inventario.size()) {
        	
            // Obtener el elemento actual utilizando el operador de módulo
            String ruta = rutas.get(i % rutas.size());

            CButton boton = new CButton(ruta);
            boton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					mostrarDetalle();
					
				}});
            add(boton);
            i++;
            
        }
        
        TButton iniciarSesion = new TButton("Iniciar Sesion", () -> {
			nav.agregarPagina(new IniciarSesionCliente(nav,gestor));
			return null;
		});
		TButton registrarCliente = new TButton("Sign in", () -> {
			nav.agregarPagina(new RegistrarClienteApp(nav, gestor));
			return null;
		});
		
		// iniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(iniciarSesion);
		add(registrarCliente);
		

    	
    }
    
    private static ArrayList<String> leerCSV(String rutaArchivo) {
    	
    	ArrayList<String> rutas = new ArrayList<String> ();
    	
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String[] linea;

           
            String line;
			while ((line = br.readLine()) != null)
			{
				String[] info = line.split(";");
				for(int i=0;i<=info.length;i++) {
				rutas.add(info[i]);
				}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return rutas;
    }
    
    private void mostrarDetalle() {
        int indiceSeleccionado = listaVehiculos.getSelectedIndex();

        if (indiceSeleccionado != -1) {
            String vehiculoSeleccionado = modeloLista.getElementAt(indiceSeleccionado);

           

            JFrame detalleVentana = new JFrame("Detalles del Vehículo");
            detalleVentana.setSize(200, 100);
            detalleVentana.setLayout(new FlowLayout());
            detalleVentana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            JLabel detalleLabel = new JLabel(vehiculoSeleccionado);
            detalleVentana.add(detalleLabel);

            detalleVentana.setVisible(true);
        }
    }
    
    
}
