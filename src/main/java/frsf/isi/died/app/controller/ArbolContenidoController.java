package frsf.isi.died.app.controller;

import java.util.List;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.ArbolContenidoPanel;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class ArbolContenidoController {
	
	private ArbolContenidoPanel panel;
	private MaterialCapacitacionDao materialDAO;
	private List<MaterialCapacitacion> lista;
	
	public ArbolContenidoController(ArbolContenidoPanel panelContenido) {
		this.panel = panelContenido;
		this.panel.setController(this);
		this.materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public List<MaterialCapacitacion> listaMateriales() {
		lista = materialDAO.listaMateriales();
		
		for(MaterialCapacitacion aux : lista) {
			aux.getContenido().setValor(aux.getTitulo());
		}
		
		return lista;
	}
	
	public void crearPanelContenido() {
		this.panel.construir();
	}
	
	public ArbolContenidoPanel getPanelContenido() {
		return this.panel;
	}
}
