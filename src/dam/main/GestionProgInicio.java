package dam.main;

import java.awt.EventQueue;
import java.io.IOException;

import dam.control.GestionProgControlador;
import dam.view.VPrograma;

public class GestionProgInicio {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VPrograma ventana = null;
				try {
					ventana = new VPrograma();
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
				GestionProgControlador control = new GestionProgControlador(ventana);
				
				ventana.setControlador(control);
				
				ventana.hacerVisible();
			}
		});
	}

}
