package frsf.isi.died.app.controller;

import java.util.Date;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.app.vista.material.VideoPanel;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Video;

public class VideoController {

	private VideoPanel panelVideo;
	private MaterialCapacitacionDao materialDAO;
	
	public VideoController(VideoPanel panel) {
		this.panelVideo = panel;
		this.panelVideo.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void agregarVideo(String titulo,Double costo,Integer duracion, Integer calificacion, Date fechaPub, Relevancia relevancia, String tema) {	
		Video v = new Video(0,titulo, costo, duracion, calificacion, fechaPub, relevancia, tema);
		v.setPageRank(1.0);
		materialDAO .agregarVideo(v);
		this.panelVideo.setListaVideos(materialDAO.listaVideos(),true);
	}
	
	public void crearPanel() {		
		this.panelVideo.setListaVideos(materialDAO.listaVideos(),false);
		this.panelVideo.construir();
	}

	public VideoPanel getPanelVideo() {
		return panelVideo;
	}

	public void setPanelVideo(VideoPanel panelVideo) {
		this.panelVideo = panelVideo;
	}
	
	
}