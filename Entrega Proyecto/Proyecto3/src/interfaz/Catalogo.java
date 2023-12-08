package interfaz;

import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import clases.Inventario;
import clases.SistemaAlquiler;
import clases.Vehiculo;

public class Catalogo extends JFrame{

	private DefaultListModel<String> modeloLista;
    private JList<String> listaVehiculos;
    
    public Catalogo(Inventario inventario) {
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
    	setSize(300, 200);
    	
    	
    	Map<String, Vehiculo> vehiculos= inventario.getVehiculoMap();

    	
    }
    
    
}
