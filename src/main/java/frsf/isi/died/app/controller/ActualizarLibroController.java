package frsf.isi.died.app.controller;

import java.util.Date;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.ActualizarLibroPanel;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Relevancia;

public class ActualizarLibroController {

	private ActualizarLibroPanel panelActualizarLibro;
	private MaterialCapacitacionDao materialDAO;
	
	public ActualizarLibroController(ActualizarLibroPanel panel) {
		this.panelActualizarLibro = panel;
		this.panelActualizarLibro.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void agregarLibro(String titulo,Double costo,Double precio,Integer paginas, Integer calificacion, Date fechaPub, Relevancia relevancia) {	
		Libro l = new Libro(0,titulo, costo, precio, paginas, calificacion, fechaPub, relevancia);
		materialDAO .agregarLibro(l);
	}
	
	public void crearPanelActualizarLibro() {
		this.panelActualizarLibro.construir();
	}
	
	public ActualizarLibroPanel getPanelActualizarLibro() {
		return this.panelActualizarLibro;
	}
}
