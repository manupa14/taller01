package frsf.isi.died.app.controller;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frsf.isi.died.app.vista.grafo.ControlPanel;
import frsf.isi.died.app.vista.grafo.GrafoPanel;
import frsf.isi.died.app.vista.material.ActualizarLibroPanel;
import frsf.isi.died.app.vista.material.ActualizarVideoPanel;
import frsf.isi.died.app.vista.material.BorrarLibroPanel;
import frsf.isi.died.app.vista.material.BorrarVideoPanel;
import frsf.isi.died.app.vista.material.BuscarLibroPanel;
import frsf.isi.died.app.vista.material.BuscarVideoPanel;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.app.vista.material.PageRankPanel;
import frsf.isi.died.app.vista.material.VideoPanel;
import frsf.isi.died.app.vista.material.WishlistPanel;

public class MenuController {

	private JFrame framePrincipal;
	
	public MenuController(JFrame f) {
		this.framePrincipal = f;
	}
	
	public void showView(TiposAcciones accion) {
		switch (accion) {
		case ABM_LIBROS:
			LibroPanel panelLibros = new LibroPanel();
			LibroController controller = new LibroController(panelLibros);
			controller.crearPanel();
			framePrincipal.setContentPane(controller.getPanelLibro());
			break;
		case ABM_VIDEOS:
			VideoPanel panelVideos = new VideoPanel();
			VideoController controllervid = new VideoController(panelVideos);
			controllervid.crearPanel();
			framePrincipal.setContentPane(controllervid.getPanelVideo());
			break;
		case BORRAR_LIBRO:
			BorrarLibroPanel panelBorrarLibros = new BorrarLibroPanel();
			BorrarLibroController controllerBorrarLibro = new BorrarLibroController(panelBorrarLibros);
			controllerBorrarLibro.crearPanelBorrarLibros();
			framePrincipal.setContentPane(controllerBorrarLibro.getPanelBorrarLibro());
			break;
		case BORRAR_VIDEO:
			BorrarVideoPanel panelBorrarVideos = new BorrarVideoPanel();
			BorrarVideoController controllerBorrarVideo = new BorrarVideoController(panelBorrarVideos);
			controllerBorrarVideo.crearPanelBorrarVideos();
			framePrincipal.setContentPane(controllerBorrarVideo.getPanelBorrarVideo());
			break;
		case ACTUALIZAR_LIBRO:
			ActualizarLibroPanel panelActualizarLibro = new ActualizarLibroPanel();
			ActualizarLibroController controllerActualizarLibro = new ActualizarLibroController(panelActualizarLibro);
			controllerActualizarLibro.crearPanelActualizarLibro();
			framePrincipal.setContentPane(controllerActualizarLibro.getPanelActualizarLibro());
			break;
		case ACTUALIZAR_VIDEO:
			ActualizarVideoPanel panelActualizarVideo = new ActualizarVideoPanel();
			ActualizarVideoController controllerActualizarVideo = new ActualizarVideoController(panelActualizarVideo);
			controllerActualizarVideo.crearPanelActualizarVideo();
			framePrincipal.setContentPane(controllerActualizarVideo.getPanelActualizarVideo());
			break;
		case BUSCAR_LIBRO:
			BuscarLibroPanel panelBuscarLibro = new BuscarLibroPanel();
			BuscarLibroController controllerBuscarLibro = new BuscarLibroController(panelBuscarLibro);
			controllerBuscarLibro.crearPanelBuscarLibro();
			framePrincipal.setContentPane(controllerBuscarLibro.getPanelBuscarLibro());
			break;
		case BUSCAR_VIDEO:
			BuscarVideoPanel panelBuscarVideo = new BuscarVideoPanel();
			BuscarVideoController controllerBuscarVideo = new BuscarVideoController(panelBuscarVideo);
			controllerBuscarVideo.crearPanelBuscarVideo();
			framePrincipal.setContentPane(controllerBuscarVideo.getPanelBuscarVideo());
			break;
		case WISHLIST:
			WishlistPanel panelWishlist = new WishlistPanel();
			WishlistController controllerWishlist = new WishlistController(panelWishlist);
			controllerWishlist.crearPanelWishlist();
			framePrincipal.setContentPane(controllerWishlist.getPanelWishlist());
			break;
		case PAGE_RANK:
			PageRankPanel panelPageRank = new PageRankPanel();
			PageRankController controllerPageRank = new PageRankController(panelPageRank);
			controllerPageRank.crearPanelPageRank();
			framePrincipal.setContentPane(controllerPageRank.getPanelPageRank());
			break;
		case VER_GRAFO:
			JPanel panel = new JPanel(new BorderLayout());
			ControlPanel controlPanel = new ControlPanel();
			GrafoPanel grafoPanel = new GrafoPanel();
			GrafoController grfController = new GrafoController(grafoPanel,controlPanel);
			panel.add(controlPanel , BorderLayout.PAGE_START);
			panel.add(grafoPanel , BorderLayout.CENTER);
			
			framePrincipal.setContentPane(panel);
			break;
		default:
			break;
		}
		framePrincipal.pack();

	}
	
	
}
