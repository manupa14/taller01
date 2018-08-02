package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.app.controller.BuscarLibroController;
import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;

public class BuscarLibroPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JComboBox comboBox;
	private JButton btnBuscar;
	private JButton btnAgregar;
	private JTable tabla;
	
	private BuscarLibroController controller;
	
	private List<ArrayList<String>> resultadoBusqueda;
	
	private MaterialCapacitacionDao materialDAO;

	public BuscarLibroPanel() {
		this.setLayout(new GridBagLayout());
	}
	
	public void construir() {
		
		GridBagConstraints gridConst = new GridBagConstraints();
		
		this.materialDAO = new MaterialCapacitacionDaoDefault();
		
		DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Titulo");
        model.addColumn("Precio Compra");
        model.addColumn("Costo Publicacion");
        model.addColumn("Paginas");
        model.addColumn("Calificacion");
        model.addColumn("Fecha de Publicacion");
        model.addColumn("Relevancia");
		
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
				resultadoBusqueda = controller.buscarLibroPorTitulo(respuestaTitulo);
				
				for(ArrayList<String> aux : resultadoBusqueda) {
		        	model.addRow(new Object[] {aux.get(0), aux.get(1), aux.get(2), aux.get(3), aux.get(4), aux.get(5), aux.get(6), aux.get(7)});
		        }
				break;
			case "Calificacion":
				String respuestaCalificacion = JOptionPane.showInputDialog("Ingrese Calificacion a buscar");
				resultadoBusqueda = controller.buscarLibroPorCalificacion(respuestaCalificacion);
				
				for(ArrayList<String> aux : resultadoBusqueda) {
		        	model.addRow(new Object[] {aux.get(0), aux.get(1), aux.get(2), aux.get(3), aux.get(4), aux.get(5), aux.get(6), aux.get(7)});
		        }
				break;
			case "Fecha de publicacion":
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				
				String respuestaFechaDesde = JOptionPane.showInputDialog("Ingrese desde que fecha");
				String respuestaFechaHasta = JOptionPane.showInputDialog("Ingrese hasta que fecha");
				resultadoBusqueda = controller.buscarLibroPorFecha(respuestaFechaDesde, respuestaFechaHasta);
				
				for(ArrayList<String> aux : resultadoBusqueda) {
		        	model.addRow(new Object[] {aux.get(0), aux.get(1), aux.get(2), aux.get(3), aux.get(4), aux.get(5), aux.get(6), aux.get(7)});
		        }
				try {
					Date fechaDesde = formato.parse(respuestaFechaDesde);
					Date fechaHasta = formato.parse(respuestaFechaHasta);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
		});
		gridConst.gridx=2;
		this.add(btnBuscar, gridConst);
		
		btnAgregar = new JButton("Agregar a Wishlist");
		btnAgregar.addActionListener(e -> {
			Libro aux = new Libro();
			aux.loadFromStringRow(resultadoBusqueda.get(tabla.getSelectedRow()));
			
			materialDAO.agregarAWishlist(aux);
			
			JOptionPane.showMessageDialog(this, "Se agrego el libro a la wishlist correctamente");
		});
		gridConst.gridx=3;
		this.add(btnAgregar, gridConst);
        
		tabla = new JTable(model);
		tabla.setFillsViewportHeight(true);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) tabla.getModel());
		tabla.setRowSorter(sorter);
		
		int indiceID = 0;
		int indiceTitulo = 1;
		int indicePrecioCompra = 2;
		int indiceCostoPub = 3;
		int indicePaginas = 4;
		int indiceCalificacion = 5;
		int indiceFecha = 6;
		int indiceRelevancia = 7;
		
		sorter.setSortable(indiceID, false);
		sorter.setSortable(indiceCostoPub, false);
		sorter.setSortable(indicePaginas, false);
		
		sorter.setComparator(indiceTitulo, new Comparator<String>() {
			 @Override
			 public int compare(String name1, String name2) {
				 return name1.compareToIgnoreCase(name2);
			 }
		});
		
		sorter.setComparator(indiceCalificacion, new Comparator<String>() {
			@Override
			public int compare(String name1, String name2) {
				return name1.compareTo(name2);
			}
		});
		
		sorter.setComparator(indicePrecioCompra, new Comparator<String>() {
			@Override
			public int compare(String name1, String name2) {
				return name1.compareTo(name2);
			}
		});
		
		sorter.setComparator(indiceFecha, new Comparator<String>() {
			@Override
			public int compare(String name1, String name2) {
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				
				try {
					Date fecha1 = formato.parse(name1);
					Date fecha2 = formato.parse(name2);
					
					if(fecha1.before(fecha2)) {
						return 1;
					} else {
						if(fecha1.equals(fecha2)) {
							return 0;
						} else {
							return -1;
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
		
		sorter.setComparator(indiceRelevancia, new Comparator<String>() {
			@Override
			public int compare(String name1, String name2) {
				switch(name1) {
				case "ALTA":
					if(name2 == "ALTA") {
						return 0;
					} else {
						return 1;
					}
				case "MEDIA":
					if(name2 == "ALTA") {
						return -1;
					} else {
						if(name2 == "MEDIA") {
							return 0;
						} else {
							return 1;
						}
					}
				case "BAJA":
					if(name2 == "BAJA") {
						return 0;
					} else {
						return -1;
					}
				default:
					return 0;
				}
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
	
	public void setController(BuscarLibroController controller) {
		this.controller = controller;
	}
	
	public BuscarLibroController getController() {
		return controller;
	}
}
