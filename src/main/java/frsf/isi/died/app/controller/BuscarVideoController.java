package frsf.isi.died.app.controller;

import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.BuscarVideoPanel;

import frsf.isi.died.tp.modelo.productos.Video;

public class BuscarVideoController {

	private BuscarVideoPanel buscarVideoPanel;
	private MaterialCapacitacionDao materialDAO;
		
	public BuscarVideoController(BuscarVideoPanel panel) {
		this.buscarVideoPanel = panel;
		this.buscarVideoPanel.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
		
	public List<ArrayList<String>> buscarVideoPorTitulo(String dato) {
		return materialDAO.buscarVideoPorTitulo(dato);
	}
	
	public List<ArrayList<String>> buscarVideoPorCalificacion(String dato) {
		return materialDAO.buscarVideoPorCalificacion(dato);
	}
	
	public List<ArrayList<String>> buscarVideoPorFecha(String desde, String hasta) {
		return materialDAO.buscarVideoPorFecha(desde, hasta);
	}
		
	public void crearPanelBuscarVideo () {
		this.buscarVideoPanel.construir();
	}
		
	public BuscarVideoPanel getPanelBuscarVideo() {
		return this.buscarVideoPanel;
	}
		
	public void setPanelBuscarVideo(BuscarVideoPanel panelVideo) {
		this.buscarVideoPanel = panelVideo;
	}
}
