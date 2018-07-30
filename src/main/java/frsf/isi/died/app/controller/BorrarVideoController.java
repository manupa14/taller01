package frsf.isi.died.app.controller;

import java.awt.List;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.BorrarVideoPanel;

public class BorrarVideoController {
	
	private BorrarVideoPanel panelBorrarVideo;
	private MaterialCapacitacionDao materialDAO;
	
	public BorrarVideoController(BorrarVideoPanel panel) {
		this.panelBorrarVideo = panel;
		this.panelBorrarVideo.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanelBorrarVideos() {
		this.panelBorrarVideo.construir();
	}
	
	public BorrarVideoPanel getPanelBorrarVideo() {
		return panelBorrarVideo;
	}
	
}