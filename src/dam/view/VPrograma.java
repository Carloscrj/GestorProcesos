package dam.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import dam.control.GestionProgControlador;

import javax.swing.JScrollPane;
import javax.swing.JList;




public class VPrograma extends JFrame {
	
	public static final String BTN_EXCEL = "EXCEL";
	public static final String BTN_WORD = "WORD";
	public static final String BTN_PWP = "POWER P.";
	public static final String BTN_NAVEGAR = "NAVEGAR";
	
	public static final int ANCHO_VENTANA = 800;
	public static final int ALTO_VENTANA = 600;
	
	
	private JButton btnExcel;
	private JButton btnWord;
	private JButton btnPWP;
	private JButton btnNavegar;
	private JTextField txtURL;
	private JList<String> lstURL;
	private JScrollPane scrpURL;
	private DefaultListModel<String> modelo;
	
	
	public VPrograma() throws IOException {
		inicializarComponentes();
	}

	private void inicializarComponentes() throws IOException {
		setTitle("GESTIÓN DE PROGRAMAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(ANCHO_VENTANA, ALTO_VENTANA);
		
		centrarVentana();
		
		btnExcel = new JButton(BTN_EXCEL);
		btnExcel.setBounds(73, 39, 89, 23);
		getContentPane().add(btnExcel);
		
		btnWord = new JButton(BTN_WORD);
		btnWord.setBounds(337, 39, 89, 23);
		getContentPane().add(btnWord);
		
		btnPWP = new JButton(BTN_PWP);
		btnPWP.setBounds(520, 39, 141, 23);
		getContentPane().add(btnPWP);
		
		btnNavegar = new JButton(BTN_NAVEGAR);
		btnNavegar.setBounds(520, 126, 121, 23);
		getContentPane().add(btnNavegar);
		
		txtURL = new JTextField();
		txtURL.setBounds(73, 127, 353, 20);
		getContentPane().add(txtURL);
		txtURL.setColumns(10);
		
		scrpURL = new JScrollPane();
		scrpURL.setBounds(73, 213, 568, 185);
		getContentPane().add(scrpURL);
		
		lstURL = new JList<>();
		scrpURL.setViewportView(lstURL);
		lstURL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelo = new DefaultListModel<String>();
		lstURL.setModel(modelo);

		
		llenarListaFichero();
	}

	public void hacerVisible() {
		setVisible(true);
	}
	
	public void setControlador(GestionProgControlador controlador) {
		btnExcel.addActionListener(controlador);
		btnWord.addActionListener(controlador);
		btnPWP.addActionListener(controlador);
		btnNavegar.addActionListener(controlador);
		lstURL.addListSelectionListener(controlador);
		
	}
	
	private void centrarVentana() {
		setPreferredSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));  
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();                     
		Dimension ventana = this.getPreferredSize();                      
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}

	public String obtenerURL() {
		String url = "http://"+txtURL.getText();
		return url;
	}

	public void llenarListaFichero() throws IOException {
		BufferedReader bfr = new BufferedReader(new FileReader("archivo/ficheroURL.txt"));
	    String linea;
    	modelo.clear();
    	while((linea = bfr.readLine()) != null) {
	    	modelo.addElement(linea);
	    }
		bfr.close();
	}
	
	public String obtenerURLSel() {
		return lstURL.getSelectedValue();
	}

	public void setURL() {
		txtURL.setText(obtenerURLSel());
	}
	
	public boolean urlValida() {
		try {
			new URL(obtenerURL()).openStream().close();
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	
}
