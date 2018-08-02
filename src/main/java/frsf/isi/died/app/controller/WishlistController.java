package frsf.isi.died.app.controller;

import java.util.PriorityQueue;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.WishlistPanel;
import frsf.isi.died.tp.modelo.ComparadorMateriales;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class WishlistController {
	
	private MaterialCapacitacionDao materialDAO;
	private WishlistPanel panelWishlist;
	private PriorityQueue<MaterialCapacitacion> wishlist;
	
	public WishlistController(WishlistPanel panel) {
		this.panelWishlist = panel;
		this.panelWishlist.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
		wishlist = new PriorityQueue<MaterialCapacitacion>(10, new ComparadorMateriales());
	}
	
	public PriorityQueue<MaterialCapacitacion> getLista() {
		wishlist = materialDAO.getWishlist();
		return wishlist;
	}
	
	public void agregar(MaterialCapacitacion mat) {
		wishlist.add(mat);
	}
	
	public void crearPanelWishlist() {
		this.panelWishlist.construir();
	}
	
	public WishlistPanel getPanelWishlist() {
		return this.panelWishlist;
	}
	
	public void setPanelWishlist(WishlistPanel panel) {
		this.panelWishlist = panel;
	}
	
}
