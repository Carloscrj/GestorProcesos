package dam.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dam.view.VPrograma;

public class DatosUrl {
	
	static VPrograma ventana;
	

	public static void main(String[] args) {

		String url = ventana.obtenerURL();
		
		
		guardarURLtxt(url);
	}

	public static void guardarURLtxt(String url) {
		
		
		try (BufferedWriter bfw = new BufferedWriter(new FileWriter("archivo/ficheroURL.txt", true));
				BufferedReader bfr = new BufferedReader(new FileReader("archivo/ficheroURL.txt"));) { 
			
			if (bfr.nullReader() != null) {
				bfw.write("\n" + url);
			} else {
				bfw.write(url);
			}

		    bfw.flush();

		    String linea;
		    while((linea = bfr.readLine()) != null){ 
		        System.out.println(linea); 

		    }
		    
		} catch(FileNotFoundException fnfe){
		    System.out.println("El fichero no se encuentra");
		} catch(IOException ioe){
		    System.out.println("Error E/S: "+ioe);
		}

	
	}

}
