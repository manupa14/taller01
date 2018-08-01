package frsf.isi.died.app.controller;

import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.BuscarLibroPanel;

import frsf.isi.died.tp.modelo.productos.Libro;

public class BuscarLibroController {

	private BuscarLibroPanel buscarLibroPanel;
	private MaterialCapacitacionDao materialDAO;
		
	public BuscarLibroController(BuscarLibroPanel panel) {
		this.buscarLibroPanel = panel;
		this.buscarLibroPanel.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
		
	public List<ArrayList<String>> buscarLibroPorTitulo(String dato) {
		return materialDAO.buscarLibroPorTitulo(dato);
	}
	
	public List<ArrayList<String>> buscarLibroPorCalificacion(String dato) {
		return materialDAO.buscarLibroPorCalificacion(dato);
	}
	
	public List<ArrayList<String>> buscarLibroPorFecha(String desde, String hasta) {
		return materialDAO.buscarLibroPorFecha(desde, hasta);
	}
		
	public void crearPanelBuscarLibro () {
		this.buscarLibroPanel.construir();
	}
		
	public BuscarLibroPanel getPanelBuscarLibro() {
		return this.buscarLibroPanel;
	}
		
	public void setPanelBuscarLibro(BuscarLibroPanel panelLibro) {
		this.buscarLibroPanel = panelLibro;
	}
}
