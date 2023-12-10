package pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

public abstract class GeneradorPdf {
	
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
				 "    <h1>Recibo con id: " + id + "</h1>",
				 // white-space: pre-line hace que se reconozcan los \n en html
				 "    <hr>",
				 "    <div><p style=\"white-space: pre-line\">" + cuerpo + "</p></div>",
				 "    <hr>",
				 "    <p>Aprobado por:</p>",
				 "    <div style=\"margin: auto 0;"
						 		+ "width: 400px; height: 200px;"
						 		+ "background-image: url('" + tony_hawk_svg + "');"
						 		+ "background-size: contain;"
						 		+ "background-repeat: no-repeat;"
						 		+ "background-position: center center;"
						 		+ "\">",
				 "    </div>",
				 "    <p>Anthony \"Tony\" Frank Hawk</p>",
				 "    <p><i><strong>CEO de Pro Wheels Car Rentals Inc.</strong></i></p>",
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
	
	private static final String tony_hawk_svg = "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj4KPHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZlcnNpb249IjEuMSIgd2lkdGg9IjkyOHB4IiBoZWlnaHQ9IjQ2M3B4IiBzdHlsZT0ic2hhcGUtcmVuZGVyaW5nOmdlb21ldHJpY1ByZWNpc2lvbjsgdGV4dC1yZW5kZXJpbmc6Z2VvbWV0cmljUHJlY2lzaW9uOyBpbWFnZS1yZW5kZXJpbmc6b3B0aW1pemVRdWFsaXR5OyBmaWxsLXJ1bGU6ZXZlbm9kZDsgY2xpcC1ydWxlOmV2ZW5vZGQiIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIj4KPGc+PHBhdGggc3R5bGU9Im9wYWNpdHk6MSIgZmlsbD0iIzAwMDAwMCIgZD0iTSA0ODEuNSw0NS41IEMgNDg2LjE2Miw0NC44MDQyIDQ5MC4zMjksNDUuODA0MiA0OTQsNDguNUMgNDkzLjIwNyw1OC43MTg1IDQ5Mi4wNCw2OC44ODUyIDQ5MC41LDc5QyA0OTAuODIsMTEyLjc0IDQ5NC40ODYsMTQ2LjI0IDUwMS41LDE3OS41QyA1MDUuODg0LDE3OC4wOTMgNTA5Ljg4NCwxNzguOTI2IDUxMy41LDE4MkMgNTEwLjQyLDE4NC41MjEgNTA3LjQyLDE4Ny4wMjEgNTA0LjUsMTg5LjVDIDUxMi43NiwyMjQuMDE4IDUyNy45MjcsMjU1LjAxOCA1NTAsMjgyLjVDIDU1MS42MiwyODUuNTc1IDU1My4xMiwyODguNzQyIDU1NC41LDI5MkMgNTUxLjg2MiwyOTUuNTM1IDU0OC41MjksMjk2LjIwMiA1NDQuNSwyOTRDIDU0Mi42NjcsMjkyLjE2NyA1NDAuODMzLDI5MC4zMzMgNTM5LDI4OC41QyA1MjYuODA5LDI3MS4yOTEgNTE1LjgwOSwyNTMuMjkxIDUwNiwyMzQuNUMgNTAwLjcyNSwyMjAuODM5IDQ5NS41NTksMjA3LjE3MiA0OTAuNSwxOTMuNUMgNDgzLjE2NywxOTYuODMzIDQ3NS44MzMsMjAwLjE2NyA0NjguNSwyMDMuNUMgNDY2LjE2NywyMDIuODMzIDQ2My44MzMsMjAyLjE2NyA0NjEuNSwyMDEuNUMgNDYyLjgzLDIxNC40NjIgNDY1LjE2MywyMjcuNDYyIDQ2OC41LDI0MC41QyA0NjcuNTY4LDI0NC41OTkgNDY1LjA2OCwyNDcuMjY2IDQ2MSwyNDguNUMgNDU5Ljg0MiwyNDguMDAyIDQ1OS4wMDksMjQ3LjE2OSA0NTguNSwyNDZDIDQ0OC4zMTMsMjE3LjcwOSA0NDIuODEzLDE4OC41NDIgNDQyLDE1OC41QyA0NDYuMzgzLDE1Ni40MjggNDUwLjcxNiwxNTYuNzYyIDQ1NSwxNTkuNUMgNDU1LjQzOSwxNzIuMDEgNDU3LjEwNSwxODQuMzQ0IDQ2MCwxOTYuNUMgNDY5LjA4NCwxOTEuNTQyIDQ3OC41ODQsMTg3LjU0MiA0ODguNSwxODQuNUMgNDgzLjAzNywxNTkuNzYgNDc5LjIwNCwxMzQuNzYgNDc3LDEwOS41QyA0NzYuMzMzLDk1LjgzMzMgNDc2LjMzMyw4Mi4xNjY3IDQ3Nyw2OC41QyA0NzcuNjQ4LDYwLjYwMDYgNDc5LjE0OCw1Mi45MzQgNDgxLjUsNDUuNSBaIi8+PC9nPgo8Zz48cGF0aCBzdHlsZT0ib3BhY2l0eTowLjk5OCIgZmlsbD0iIzAwMDAwMCIgZD0iTSA2MjkuNSw1Ni41IEMgNjMzLjEzLDU2Ljc2NDYgNjM2LjI5Nyw1OC4wOTc5IDYzOSw2MC41QyA2MzYuNTA4LDkzLjU4MjMgNjM5LjE3NSwxMjYuMjQ5IDY0NywxNTguNUMgNjQ4Ljc3OSwxNjQuNzIzIDY1MS4xMTIsMTcwLjcyMyA2NTQsMTc2LjVDIDY1Ny4xNzUsMTY3LjMgNjU5LjM0MiwxNTcuOCA2NjAuNSwxNDhDIDY1OC42MTksMTQyLjMxMiA2NTcuMTE5LDEzNi40NzkgNjU2LDEzMC41QyA2NTUuNTExLDEyMy4wNzUgNjU2LjE3OCwxMTUuNzQxIDY1OCwxMDguNUMgNjYwLjMwMiwxMDMuNTcyIDY2My40NjgsMTAyLjczOCA2NjcuNSwxMDZDIDY2OC44OTksMTA3LjA2MiA2NjkuODk5LDEwOC4zOTUgNjcwLjUsMTEwQyA2NjQuMzgzLDE0MS40NTggNjc3LjA1LDE1OS43OTIgNzA4LjUsMTY1QyA3NDMuMjYsMTY1LjkyOSA3NzYuOTI3LDE2MC4yNjIgODA5LjUsMTQ4QyA4MjIuNDA0LDE0Mi42MzEgODM1LjIzNywxMzcuMTMxIDg0OCwxMzEuNUMgODUwLjYyNywxMzIuMDc1IDg1MS43OTQsMTMzLjc0MSA4NTEuNSwxMzYuNUMgODUxLjMzMywxMzcuNSA4NTEuMTY3LDEzOC41IDg1MSwxMzkuNUMgODA2LjY2NCwxNjMuNzkyIDc1OS4xNjQsMTc1LjI5MiA3MDguNSwxNzRDIDY5NC4yMzgsMTcyLjkxNyA2ODEuNzM4LDE2Ny43NTEgNjcxLDE1OC41QyA2NjkuNTA5LDE2Ny4yOSA2NjcuNTA5LDE3NS45NTcgNjY1LDE4NC41QyA2NjIuOTMsMTg4IDY1OS43NjQsMTg5LjY2NyA2NTUuNSwxODkuNUMgNjUxLjQ3MiwxODkuNDM3IDY0OC4zMDUsMTg3Ljc3MSA2NDYsMTg0LjVDIDY0MC45MzksMTc2LjM3NCA2MzYuOTM5LDE2Ny43MDcgNjM0LDE1OC41QyA2MzEuODc5LDE2OC41MzMgNjI4LjM3OSwxNzguMDMzIDYyMy41LDE4N0MgNjE5LjQ3NCwxODguNzk0IDYxNS40NzQsMTg4Ljc5NCA2MTEuNSwxODdDIDYwNy45ODQsMTgzLjY1MSA2MDQuNjUxLDE4MC4xNTEgNjAxLjUsMTc2LjVDIDU5Ni43NDQsMTgxLjQxNiA1OTEuMDc4LDE4Mi43NSA1ODQuNSwxODAuNUMgNTgzLjMwNCwxODguMjU4IDU4MC42MzgsMTk1LjQyNSA1NzYuNSwyMDJDIDU2Ny4xNzEsMjA2LjExMSA1NjAuMDA0LDIwMy42MTEgNTU1LDE5NC41QyA1NTMuMzc2LDIwMi4wNjUgNTQ5Ljg3NiwyMDguNTY1IDU0NC41LDIxNEMgNTMyLjIzMSwyMTYuNzMgNTI1LjA2NCwyMTEuODk3IDUyMywxOTkuNUMgNTIwLjYzOCwxODMuNDAyIDUyMi45NzEsMTY4LjA2OCA1MzAsMTUzLjVDIDUzNy4yODQsMTQ5LjE4MSA1NDQuMTE3LDE0OS44NDcgNTUwLjUsMTU1LjVDIDU1NC44NjEsMTU0LjQwNiA1NTguNjk1LDE1NS40MDYgNTYyLDE1OC41QyA1NjIuMzEzLDE2NS44NjggNTYyLjk4LDE3My4yMDEgNTY0LDE4MC41QyA1NjQuNDM4LDE4My4zNzYgNTY1LjQzOCwxODYuMDQzIDU2NywxODguNUMgNTY5LjQ4LDE3OC42OTggNTcxLjMxMywxNjguNjk4IDU3Mi41LDE1OC41QyA1NzYuMDI5LDE1Ni4xMjMgNTc5LjY5NiwxNTUuOTU2IDU4My41LDE1OEMgNTg1Ljc4MywxNjEuNjgyIDU4Ny43ODMsMTY1LjUxNSA1ODkuNSwxNjkuNUMgNTk4Ljc2MywxNjEuNDMzIDYwNi41OTcsMTYyLjc2NiA2MTMsMTczLjVDIDYxMy44NTMsMTc1LjEyNSA2MTQuODUzLDE3NS40NTggNjE2LDE3NC41QyA2MjAuNTU0LDE2My42MTggNjIzLjIyLDE1Mi4yODQgNjI0LDE0MC41QyA2MjQuNTE4LDEzMS4zMzQgNjI1LjAxOCwxMjIuMTY4IDYyNS41LDExM0MgNjIzLjg2NCw5NC40MzkgNjI0LjE5Nyw3NS45MzkgNjI2LjUsNTcuNUMgNjI3Ljc5MSw1Ny43MzczIDYyOC43OTEsNTcuNDA0IDYyOS41LDU2LjUgWiBNIDU0Mi41LDE2MC41IEMgNTQ0LjExNywxNjEuMDM4IDU0NS43ODQsMTYxLjM3MSA1NDcuNSwxNjEuNUMgNTQ1Ljg5OSwxNzQuNTcgNTQyLjczMiwxODcuMjM3IDUzOCwxOTkuNUMgNTM2LjA3NCwxODYuMDk3IDUzNy41NzQsMTczLjA5NyA1NDIuNSwxNjAuNSBaIi8+PC9nPgo8Zz48cGF0aCBzdHlsZT0ib3BhY2l0eTowLjk5OSIgZmlsbD0iIzAwMDAwMCIgZD0iTSAxOTQuNSwxNTcuNSBDIDE5My41MDgsMTU3LjMyOCAxOTIuODQyLDE1Ny42NjIgMTkyLjUsMTU4LjVDIDE2Mi4xMjYsMTcyLjkzNSAxMzQuOTU5LDE5MS45MzUgMTExLDIxNS41QyAxMDcuMDQ1LDIyMC41NzggMTAzLjIxMSwyMjUuNzQ0IDk5LjUsMjMxQyAxMDIuNTc2LDIzMS4zNDYgMTA1LjU3NiwyMzIuMDEyIDEwOC41LDIzM0MgMTEwLjUyNywyMzQuMzk2IDExMC44NiwyMzYuMDYyIDEwOS41LDIzOEMgMTA3LjQ2NiwyMzkuMDA2IDEwNS4zLDIzOS41MDYgMTAzLDIzOS41QyA5Ny41MDc4LDIzOC45MTcgOTIuMDA3OCwyMzguNDE3IDg2LjUsMjM4QyA4My4yMzEsMjM3LjQ0MyA4MS4yMzEsMjM1LjYxIDgwLjUsMjMyLjVDIDg4LjEyMTIsMjE5LjE0OCA5Ny43ODc5LDIwNy4zMTUgMTA5LjUsMTk3QyAxNDAuODM5LDE2OC43MTUgMTc2LjgzOSwxNDguMzgyIDIxNy41LDEzNkMgMjI2Ljg0OCwxMzIuNzI1IDIzNi4xODEsMTMyLjcyNSAyNDUuNSwxMzZDIDI0OC41NzYsMTM2LjkxMSAyNTAuOTA5LDEzOC43NDQgMjUyLjUsMTQxLjVDIDI1MS45NzQsMTQzLjIzIDI1MC45NzQsMTQ0LjU2NCAyNDkuNSwxNDUuNUMgMjQwLjc5MiwxNDIuNzMgMjMyLjEyNSwxNDIuODk3IDIyMy41LDE0NkMgMjEzLjQ2NywxNDkuMTcyIDIwMy44LDE1My4wMDUgMTk0LjUsMTU3LjUgWiIvPjwvZz4KPGc+PHBhdGggc3R5bGU9Im9wYWNpdHk6MSIgZmlsbD0iIzAwMDAwMCIgZD0iTSAxOTQuNSwxNTcuNSBDIDE5OC42MDMsMTU2Ljc2OSAyMDIuMTAzLDE1Ny43NjkgMjA1LDE2MC41QyAyMDIuODQsMTkyLjEgMjA0Ljg0LDIyMy40MzMgMjExLDI1NC41QyAyMTkuNjY2LDI5Ni44MzggMjMyLjQ5OSwzMzcuODM4IDI0OS41LDM3Ny41QyAyNDguOTYsMzc4Ljc0OCAyNDguMjkzLDM3OS45MTUgMjQ3LjUsMzgxQyAyNDQuMjk4LDM4Mi40ODMgMjQwLjk2NCwzODIuODE3IDIzNy41LDM4MkMgMjExLjYxOCwzMjcuNjc0IDE5NS40NTEsMjcwLjUwNyAxODksMjEwLjVDIDE4OC4zMzMsMTk3LjgzMyAxODguMzMzLDE4NS4xNjcgMTg5LDE3Mi41QyAxODkuMDM2LDE2Ny40NTcgMTkwLjIwMywxNjIuNzkxIDE5Mi41LDE1OC41QyAxOTMuNDkyLDE1OC42NzIgMTk0LjE1OCwxNTguMzM4IDE5NC41LDE1Ny41IFoiLz48L2c+CjxnPjxwYXRoIHN0eWxlPSJvcGFjaXR5OjAuMjUxIiBmaWxsPSIjMDAwMDAwIiBkPSJNIDc4Ni41LDE3MS41IEMgNzg3LjgzMywxNzIuMTY3IDc4Ny44MzMsMTcyLjE2NyA3ODYuNSwxNzEuNSBaIi8+PC9nPgo8Zz48cGF0aCBzdHlsZT0ib3BhY2l0eTowLjI1MSIgZmlsbD0iIzAwMDAwMCIgZD0iTSAxMzcuNSwxOTMuNSBDIDEzOC44MzMsMTk0LjE2NyAxMzguODMzLDE5NC4xNjcgMTM3LjUsMTkzLjUgWiIvPjwvZz4KPGc+PHBhdGggc3R5bGU9Im9wYWNpdHk6MSIgZmlsbD0iIzAwMDAwMCIgZD0iTSAyNjguNSwyMDcuNSBDIDI3OS42MjcsMjAzLjU4NiAyODcuNDYxLDIwNi45MTkgMjkyLDIxNy41QyAyOTQuMTgxLDIyNC41NiAyOTYuNTE0LDIzMS41NiAyOTksMjM4LjVDIDMwMi4wMTksMjI0LjQyMiAzMDUuMzUzLDIxMC40MjIgMzA5LDE5Ni41QyAzMTQuNDAyLDE5Mi41NjggMzE5LjczNiwxOTIuNTY4IDMyNSwxOTYuNUMgMzMxLjQzMSwyMDguMTIxIDMzNi4wOTgsMjIwLjQ1NSAzMzksMjMzLjVDIDM0MC43NjMsMjIxLjg2OCAzNDIuMjYzLDIxMC4yMDEgMzQzLjUsMTk4LjVDIDM0Ny45ODUsMTk1LjAyMSAzNTIuNjUyLDE5NC42ODcgMzU3LjUsMTk3LjVDIDM1OS42NjIsMjAyLjQ5NiAzNjIuMzI5LDIwNy4xNjIgMzY1LjUsMjExLjVDIDM3MC4zNTEsMjA2LjgxNSAzNzUuMzUxLDIwMi4zMTUgMzgwLjUsMTk4QyAzOTcuMDg1LDE5MS4zNzggNDA3LjU4NSwxOTYuODc4IDQxMiwyMTQuNUMgNDEzLjY0MSwyMTAuNjc3IDQxNi40NzQsMjA4LjY3NyA0MjAuNSwyMDguNUMgNDIwLjc3MSwyMTYuMTg4IDQxOS4yNzEsMjIzLjUyMiA0MTYsMjMwLjVDIDQxNi41NjcsMjQ3Ljg5NCA0MTUuMjMzLDI2NS4yMjggNDEyLDI4Mi41QyA0MTEuMDY2LDI4Ni42MzcgNDA5LjczMiwyOTAuNjM3IDQwOCwyOTQuNUMgNDA1LjI1NiwyOTYuNzU1IDQwMi4wODksMjk4LjA4OCAzOTguNSwyOTguNUMgMzk2LjM2NCwyOTcuNTI4IDM5NS4xOTcsMjk1Ljg2MSAzOTUsMjkzLjVDIDM5My4xMzgsMjc0LjcyOCAzOTUuMzA1LDI1Ni4zOTUgNDAxLjUsMjM4LjVDIDQwMC4wNywyMjcuNzggMzk3LjIzNywyMTcuNDQ3IDM5MywyMDcuNUMgMzkyLjE2MywyMDUuOTkzIDM5MC45OTYsMjA1LjQ5MyAzODkuNSwyMDZDIDM4NC40MjcsMjExLjc0NCAzNzguNzYsMjE2Ljc0NCAzNzIuNSwyMjFDIDM2Ny4yMiwyMjIuODI4IDM2MS44ODYsMjIyLjk5NSAzNTYuNSwyMjEuNUMgMzU1Ljk0NywyMzMuMTU0IDM1My43OCwyNDQuNDg4IDM1MCwyNTUuNUMgMzQ2LjA5MSwyNjEuODU3IDM0MC41OTEsMjYzLjY5IDMzMy41LDI2MUMgMzMyLjIxMywyNTkuNTkgMzMxLjA0NiwyNTguMDkgMzMwLDI1Ni41QyAzMjUuNzMyLDI0NS4zNjMgMzIyLjA2NiwyMzQuMDMgMzE5LDIyMi41QyAzMTYuNDIsMjM2LjQ4OSAzMTIuNzUzLDI1MC4xNTYgMzA4LDI2My41QyAzMDcuMTY3LDI2NC4zMzMgMzA2LjMzMywyNjUuMTY3IDMwNS41LDI2NkMgMjk5Ljk5NywyNjguNjk5IDI5NS4xNjQsMjY3Ljg2NiAyOTEsMjYzLjVDIDI4Ni41MzgsMjUzLjQ0OCAyODIuODcxLDI0My4xMTQgMjgwLDIzMi41QyAyNzcuOTgzLDI0OC4yNTYgMjc0LjMxNiwyNjMuNTg5IDI2OSwyNzguNUMgMjYyLjMxNCwyODQuNzIzIDI1NS4zMTQsMjg1LjA1NiAyNDgsMjc5LjVDIDI0Ni4wNzMsMjc2LjA1MiAyNDQuNzM5LDI3Mi4zODUgMjQ0LDI2OC41QyAyNDIuMDE4LDI0OC40OTkgMjQyLjAxOCwyMjguNDk5IDI0NCwyMDguNUMgMjQ0LjIwMywyMDEuMDMgMjQ3Ljg3LDE5Ni42OTcgMjU1LDE5NS41QyAyNTguODYzLDE5Ni4xMTYgMjYyLjY5NiwxOTYuOTUgMjY2LjUsMTk4QyAyNzAuNzcxLDIwMC41NjcgMjcxLjQzOCwyMDMuNzM0IDI2OC41LDIwNy41IFogTSAyNjguNSwyMDcuNSBDIDI2NS45OCwyMjQuNjIyIDI2Mi44MTMsMjQxLjYyMiAyNTksMjU4LjVDIDI1Ni43NTQsMjQxLjc3OCAyNTcuMDg3LDIyNS4xMTEgMjYwLDIwOC41QyAyNjAuNjc0LDIwNy40MjIgMjYxLjY3NCwyMDYuNzU1IDI2MywyMDYuNUMgMjY0LjgyNSwyMDYuOTQzIDI2Ni42NTgsMjA3LjI3NyAyNjguNSwyMDcuNSBaIi8+PC9nPgo8L3N2Zz4K";
}
