package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import frsf.isi.died.app.controller.BuscarLibroController;

public class BuscarLibroPanel extends JPanel{
	
	private JLabel lblTitulo;
	private JComboBox comboBox;
	private JButton btnBuscar;

	private BuscarLibroController controller;

	public BuscarLibroPanel() {
		this.setLayout(new GridBagLayout());
	}
	
	public void construir() {
		
		GridBagConstraints gridConst = new GridBagConstraints();
		
		lblTitulo = new JLabel("Seleccionar criterio de busqueda: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblTitulo, gridConst);
		
		comboBox = new JComboBox();
		comboBox.addItem("Titulo");
		comboBox.addItem("Calificacion");
		comboBox.addItem("Fecha de publicacion");
		gridConst.gridx=1;
		this.add(comboBox, gridConst);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> {
			switch((String) comboBox.getSelectedItem()) {
			case "Titulo":
				String respuestaTitulo = JOptionPane.showInputDialog("Ingrese Titulo a buscar");
				break;
			case "Calificacion":
				String respuestaCalificacion = JOptionPane.showInputDialog("Ingrese Calificacion a buscar");
				break;
			case "Fecha de publicacion":
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				
				String respuestaFechaDesde = JOptionPane.showInputDialog("Ingrese desde que fecha");
				String respuestaFechaHasta = JOptionPane.showInputDialog("Ingrese hasta que fecha");
				
				try {
					Date fechaDesde = formato.parse(respuestaFechaDesde);
					Date fechaHasta = formato.parse(respuestaFechaHasta);
					
					System.out.println(formato.format(fechaDesde));
					System.out.println(formato.format(fechaHasta));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		gridConst.gridx=2;
		this.add(btnBuscar, gridConst);
	}
	
	public void setController(BuscarLibroController controller) {
		this.controller = controller;
	}
	
	public BuscarLibroController getController() {
		return controller;
	}
}
