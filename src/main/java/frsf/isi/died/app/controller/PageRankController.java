package frsf.isi.died.app.controller;

import java.util.List;
import java.util.ArrayList;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.PageRankPanel;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class PageRankController {
	
	private PageRankPanel panelPageRank;
	private MaterialCapacitacionDao materialDAO;
	
	public PageRankController(PageRankPanel panel) {
		this.panelPageRank = panel;
		this.panelPageRank.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public List<ArrayList<String>> buscarMaterialPorTema(String dato) {
		List<ArrayList<String>> aux = new ArrayList<ArrayList<String>>();
		aux.addAll(materialDAO.buscarLibroPorTema(dato));
		aux.addAll(materialDAO.buscarVideoPorTema(dato));
		return aux;
	}
	
	public void calcularPageRank(List<MaterialCapacitacion> materiales) {
		materialDAO.calcularPageRank(materiales);
	}
	
	public void crearPanelPageRank() {
		this.panelPageRank.construir();
	}
	
	public PageRankPanel getPanelPageRank() {
		return this.panelPageRank;
	}
	
	public void setPanelPageRank(PageRankPanel panel) {
		this.panelPageRank = panel;
	}
	
}
