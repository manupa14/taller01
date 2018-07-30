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
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.app.vista.material.VideoPanel;

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
