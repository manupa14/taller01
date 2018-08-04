package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import frsf.isi.died.app.controller.BuscarLibroController;
import frsf.isi.died.app.controller.BuscarVideoController;
import frsf.isi.died.app.controller.WishlistController;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class WishlistPanel extends JPanel{
	
	private WishlistController controller;
	private PriorityQueue<MaterialCapacitacion> wishlist;
	private JTable tabla;
	private JLabel lblTitulo;
	private DefaultTableModel model;
	
	public WishlistPanel() {
		this.setLayout(new GridBagLayout());
	}
	
	public void construir() {
		
		GridBagConstraints gridConst = new GridBagConstraints();
		
		wishlist = controller.getLista();
		
		lblTitulo = new JLabel("WISHLIST");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblTitulo, gridConst);
		
		model = new DefaultTableModel();
		model.addColumn("ID");
        model.addColumn("Titulo");
        model.addColumn("Precio Compra");
        model.addColumn("Costo Publicacion");
        model.addColumn("Calificacion");
        model.addColumn("Fecha Publicacion");
        model.addColumn("Relevancia");
        model.addColumn("Tema");
        
        for(MaterialCapacitacion aux : wishlist) {
        	model.addRow(new Object[] {aux.getId(), aux.getTitulo(), aux.precio(), aux.getCosto(), aux.getCalificacion(), aux.getFechaPub(), aux.getRelevancia(), aux.getTema()});
        }
        
		tabla = new JTable(model);
		tabla.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(tabla);
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.gridwidth=13;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;	
		this.add(scrollPane, gridConst);
		
	}
	
	public void setController(WishlistController wishlistController) {
		this.controller = wishlistController;
	}
	
	public WishlistController getController() {
		return this.controller;
	}
}
