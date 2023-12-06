package testcargadatos;
import clases.CSVReader;
import clases.SistemaAlquiler;
import clases.ContenedorDeDatos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestCSVReader {
	
	private static SistemaAlquiler sa;
	private static ContenedorDeDatos cd;
	private static CSVReader csv;
	
	
	
	@BeforeAll
	public void setUp() throws FileNotFoundException, ClassNotFoundException, IOException
	{
		cd = new ContenedorDeDatos();
		
		sa = new SistemaAlquiler();
		
		csv = new CSVReader(sa);
		
	}
	
	@AfterAll
	public void tearDown() 
	{
		cd = null;
		
		sa = null;
		
		csv = null;
	}
	
	@Test
	public void testSum() {
		int ans = 2; 
		assertEquals(ans,1+1);
	}
	
	
	
	
	
	
	
	

}
