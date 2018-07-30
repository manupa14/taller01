package frsf.isi.died.app.controller;

import java.awt.List;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.BorrarLibroPanel;

public class BorrarLibroController {
	
	private BorrarLibroPanel panelBorrarLibro;
	private MaterialCapacitacionDao materialDAO;
	
	public BorrarLibroController(BorrarLibroPanel panel) {
		this.panelBorrarLibro = panel;
		this.panelBorrarLibro.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanelBorrarLibros() {
		this.panelBorrarLibro.construir();
	}
	
	public BorrarLibroPanel getPanelBorrarLibro() {
		return panelBorrarLibro;
	}
	
}
