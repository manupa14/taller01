package frsf.isi.died.app.controller;

import java.util.Date;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.ActualizarVideoPanel;
import frsf.isi.died.tp.modelo.productos.Video;
import frsf.isi.died.tp.modelo.productos.Relevancia;

public class ActualizarVideoController {

	private ActualizarVideoPanel panelActualizarVideo;
	private MaterialCapacitacionDao materialDAO;
	
	public ActualizarVideoController(ActualizarVideoPanel panel) {
		this.panelActualizarVideo = panel;
		this.panelActualizarVideo.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void agregarVideo(String titulo,Double costo,Integer duracion, Integer calificacion, Date fechaPub, Relevancia relevancia, String tema) {	
		Video v = new Video(0,titulo, costo, duracion, calificacion, fechaPub, relevancia, tema);
		v.setPageRank(1.0);
		materialDAO .agregarVideo(v);
	}
	
	public void crearPanelActualizarVideo() {
		this.panelActualizarVideo.construir();
	}
	
	public ActualizarVideoPanel getPanelActualizarVideo() {
		return this.panelActualizarVideo;
	}
}
