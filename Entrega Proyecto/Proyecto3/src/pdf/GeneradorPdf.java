package pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

public class GeneradorPdf {
	
	public static void guardarPdf(String id, String cuerpo) throws FileNotFoundException, IOException {
        File pdfDest = new File(DIR(id));
         // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(generarHTML(id, cuerpo), new FileOutputStream(pdfDest), converterProperties);
	}
	
	private static final String generarHTML(String id, String cuerpo) {
		return String.join("\r\n", "<!DOCTYPE html>",
				 "<html lang=\"en\">",
				 "  <head>",
				 "    <meta charset=\"UTF-8\">",
				 "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">",
				 "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">",
				 "    <title>HTML 5 Boilerplate</title>",
//				 "    <link rel=\"stylesheet\" href=\"style.css\">",
				 "  </head>",
				 "  <body>",
				 "<h1>Recibo con id: " + id + "</h1>",
				 // white-space: pre-line hace que se reconozcan los \n en html
				 "<div><p style=\"white-space: pre-line\">" + cuerpo + "</p></div>",
//				 "	<script src=\"index.js\"></script>",
				 "  </body>",
				 "</html>");
	}
	
	private static final String DIR(String id) {
		return "./recibos_pdf/recibo_" + id + ".pdf";
	}
	
	public static void main(String[] args) {
		// prueba generar pdf
		try {
			guardarPdf("12345", "Hamburguesa ingredientes:\n  - tomate\n  - queso\n  - carne molida\n  - cebolla\n  - lechuga\n  - secret sauce");
			System.out.println("Woo generar pdf funciono");
		} catch (IOException e) {
			System.out.println("Generar pdf no funciono :(");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
