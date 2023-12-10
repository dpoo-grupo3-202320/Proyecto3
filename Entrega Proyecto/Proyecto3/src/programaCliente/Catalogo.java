package programaCliente;

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
import javax.swing.JList;
import javax.swing.JScrollPane;

import clases.Inventario;
import clases.SistemaAlquiler;
import clases.Vehiculo;



public class Catalogo extends JFrame{

	private DefaultListModel<String> modeloLista;
    private JList<String> listaVehiculos;
    
    public Catalogo(Navegador nav, GestorDatos gestor) {
    	
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
        JScrollPane scrollPane = new JScrollPane(listaVehiculos);

    	
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
    
    
}
