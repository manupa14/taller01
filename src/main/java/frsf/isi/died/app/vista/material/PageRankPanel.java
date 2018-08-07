package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import frsf.isi.died.tp.modelo.productos.Video;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

import frsf.isi.died.app.controller.PageRankController;

public class PageRankPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JTextField txtTitulo;
	private JButton btnBuscar;
	private JTable tabla;
	
	private PageRankController controller;
	
	private List<ArrayList<String>> resultadoBusqueda;
	private List<MaterialCapacitacion> materiales;
	
	public PageRankPanel() {
		this.setLayout(new GridBagLayout());
	}
	
	public void construir() {
		
		GridBagConstraints gridConst = new GridBagConstraints();
		
		DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Titulo");
        model.addColumn("Tema");
        model.addColumn("Page Rank");
        
        tabla = new JTable(model);
		tabla.setFillsViewportHeight(true);
		
		lblTitulo = new JLabel("Ingrese el tema: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblTitulo, gridConst);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(20);
		gridConst.gridx=1;
		this.add(txtTitulo, gridConst);
		
		btnBuscar = new JButton("Generar PageRank");
		btnBuscar.addActionListener(e -> {
			String tema = String.valueOf(txtTitulo.getText());
			resultadoBusqueda = controller.buscarMaterialPorTema(tema);
			
			materiales = new ArrayList<MaterialCapacitacion>();
			
			for(ArrayList<String> aux : resultadoBusqueda) {
				if(aux.size() == 8) {
					Video auxVideo = new Video();
					auxVideo.loadFromStringRow(aux);
					auxVideo.setPageRank(1.0);
					materiales.add(auxVideo);
				} else {
					if(aux.size() == 9) {
						Libro auxLibro = new Libro();
						auxLibro.loadFromStringRow(aux);
						auxLibro.setPageRank(1.0);
						materiales.add(auxLibro);
					}
				}
			}
			
			controller.calcularPageRank(materiales);
			
			for(MaterialCapacitacion mat : materiales) {
				model.addRow(new Object[] {mat.getId(), mat.getTitulo(), mat.getTema(), mat.getPageRank().toString()});
			}
		});
		gridConst.gridx=2;
		this.add(btnBuscar, gridConst);
		
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) tabla.getModel());
		tabla.setRowSorter(sorter);
		
		int indiceID = 0;
		int indiceTitulo = 1;
		int indiceTema = 2;
		int indicePageRank = 3;
		
		sorter.setSortable(indiceID, false);
		sorter.setSortable(indiceTitulo, false);
		sorter.setSortable(indiceTema, false);
		
		sorter.setComparator(indicePageRank, new Comparator<String>() {
			 @Override
			 public int compare(String name1, String name2) {
				 return name1.compareToIgnoreCase(name2);
			 }
		});
		
		scrollPane = new JScrollPane(tabla);
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.gridwidth=13;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;	
		this.add(scrollPane, gridConst);
	}
	
	public void setController(PageRankController controllerPageRank) {
		this.controller = controllerPageRank;
	}
	
	public PageRankController getController() {
		return this.controller;
	}
	
}
