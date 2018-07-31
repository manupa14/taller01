package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.BuscarLibroPanel;

public class BuscarLibroController {

		private BuscarLibroPanel buscarLibroPanel;
		private MaterialCapacitacionDao materialDAO;
		
		public BuscarLibroController(BuscarLibroPanel panel) {
			this.buscarLibroPanel = panel;
			this.buscarLibroPanel.setController(this);
			materialDAO = new MaterialCapacitacionDaoDefault();
		}
		
		public void crearPanelBuscarLibro () {
			this.buscarLibroPanel.construir();
		}
		
		public BuscarLibroPanel getPanelBuscarLibro() {
			return this.buscarLibroPanel;
		}
}
