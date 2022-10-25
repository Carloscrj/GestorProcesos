package dam.control;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dam.persistencia.DatosUrl;
import dam.view.VPrograma;

public class GestionProgControlador implements ActionListener, ListSelectionListener{

	VPrograma ventana;
	DatosUrl datos;
	
	
	
	public GestionProgControlador(VPrograma ventana) {
		this.ventana = ventana;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			if (e.getActionCommand().equals(VPrograma.BTN_EXCEL)) {
				try {
					Process ejecutarExcel = 
							new ProcessBuilder("\"C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL.EXE\"").start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (e.getActionCommand().equals(VPrograma.BTN_WORD)) {
				try {
					Process ejecutarWord = 
							new ProcessBuilder("\"C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.EXE\"").start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (e.getActionCommand().equals(VPrograma.BTN_PWP)) {
				try {
					Process ejecutarPowerPoint = 
							new ProcessBuilder("\"C:\\Program Files\\Microsoft Office\\root\\Office16\\POWERPNT.EXE\"").start();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			} else if (e.getActionCommand().equals(VPrograma.BTN_NAVEGAR)) {
				if (!ventana.urlValida()) {
					JOptionPane.showMessageDialog(ventana,
							"No se ha introducido URL o URL erronea", "URL Vacía o Erronea ", JOptionPane.ERROR_MESSAGE);
				} else {
					Desktop desktop = java.awt.Desktop.getDesktop();
					String url = ventana.obtenerURL();
					try {
						URI oURL = new URI(url);
						desktop.browse(oURL);
					} catch (URISyntaxException e3) {
						e3.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					datos.guardarURLtxt(url);
					
					try {
						ventana.llenarListaFichero();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				

			}	
		}	
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Desktop desktop = java.awt.Desktop.getDesktop();
		String urlSel = ventana.obtenerURLSel();
		try {
			URI oURL = new URI(urlSel);
			desktop.browse(oURL);
		} catch (URISyntaxException e3) {
			e3.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ventana.setURL();
	}
	
}
